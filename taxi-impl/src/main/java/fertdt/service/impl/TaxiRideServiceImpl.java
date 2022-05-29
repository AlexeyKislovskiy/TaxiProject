package fertdt.service.impl;

import fertdt.dto.request.TaxiCallRequest;
import fertdt.dto.request.UpcomingTaxiCallRequest;
import fertdt.dto.response.DriverResponse;
import fertdt.dto.response.TaxiRideResponse;
import fertdt.exception.*;
import fertdt.exception.deletion.CancelTaxiCallException;
import fertdt.exception.deletion.CancelTripException;
import fertdt.exception.notFound.DriverNotFoundException;
import fertdt.exception.notFound.TaxiRideNotFoundException;
import fertdt.exception.relationalshipConflict.*;
import fertdt.model.*;
import fertdt.repository.DriverRepository;
import fertdt.repository.TaxiRideRepository;
import fertdt.service.*;
import fertdt.util.CarUsingUtil;
import fertdt.util.DriverStatusUtil;
import fertdt.util.TaxiRideStatusUtil;
import fertdt.util.mapper.TaxiRideMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaxiRideServiceImpl implements TaxiRideService {
    private final TaxiRideRepository taxiRideRepository;
    private final TaxiRideMapper taxiRideMapper;
    private final UserService userService;
    private final CarClassService carClassService;
    private final PaymentMethodService paymentMethodService;
    private final GeographicalPointService geographicalPointService;
    private final AdditionalRequirementsService additionalRequirementsService;
    private final DriverRepository driverRepository;
    private final DriverService driverService;
    private final UserTaxiRideService userTaxiRideService;

    @Override
    public UUID callTaxi(TaxiCallRequest taxiCallRequest) {
        UpcomingTaxiCallRequest taxiCall = taxiCallRequest.getTaxiCall();
        userService.getUserById(taxiCall.getPassengerId());
        carClassService.getClassCarById(taxiCall.getCarClassId());
        paymentMethodService.getPaymentMethodById(taxiCall.getPaymentMethodId());
        if (userTaxiRideService.userHasUnfinishedTrip(taxiCall.getPassengerId()))
            throw new UserHasUnfinishedTripException();
        Optional<DriverEntity> optionalDriver = driverRepository.findById(taxiCall.getPassengerId());
        if (optionalDriver.isPresent()) {
            DriverEntity driver = optionalDriver.get();
            if (driver.getDriverStatus().equals(DriverStatus.AT_WORK))
                throw new DriverAtWorkException("Cannot call taxi as passenger while at work as driver");
        }
        TaxiRideEntity taxiRide = taxiRideMapper.toEntity(taxiCallRequest);
        List<GeographicalPointEntity> allGeographicalPoints = new ArrayList<>();
        taxiRide.setStartingPoint(geographicalPointService.getGeographicalPointFromCoordinates(taxiCall.getStartingPoint()));
        allGeographicalPoints.add(taxiRide.getStartingPoint());
        if (taxiCall.getIntermediatePoints() != null) {
            taxiRide.setIntermediatePoints(taxiCall.getIntermediatePoints().stream()
                    .map(geographicalPointService::getGeographicalPointFromCoordinates).collect(Collectors.toList()));
            allGeographicalPoints.addAll(taxiRide.getIntermediatePoints());
        }
        taxiRide.setDestination(geographicalPointService.getGeographicalPointFromCoordinates(taxiCall.getDestination()));
        allGeographicalPoints.add(taxiRide.getDestination());
        if (!geographicalPointService.correctTaxiCallGeographicalPoints(allGeographicalPoints))
            throw new GeographicalPointsConflictException();
        taxiRide = taxiRideRepository.save(taxiRide);
        additionalRequirementsService.createAdditionalRequirements(taxiRide, taxiCall.getAdditionalRequirements());
        return taxiRide.getUuid();
    }

    @Override
    public UUID cancelTaxiCall(UUID userId) {
        userService.getUserById(userId);
        TaxiRideEntity taxiRide = userTaxiRideService.getCurrentTaxiRideForUser(userId);
        if (taxiRide == null) throw new CancelTaxiCallException("User does not have an active trip");
        if (!TaxiRideStatusUtil.cancellableByPassenger(taxiRide))
            throw new CancelTaxiCallException("User cannot cancel trip in progress");
        taxiRide.setTaxiRideStatus(TaxiRideStatus.TRIP_CANCELED_BY_CLIENT);
        taxiRideRepository.save(taxiRide);
        return taxiRide.getUuid();
    }

    @Override
    public TaxiRideResponse getTaxiRideById(UUID taxiRideId) {
        return taxiRideMapper.toResponse(
                taxiRideRepository.findById(taxiRideId).orElseThrow(TaxiRideNotFoundException::new)
        );
    }

    @Override
    public void takeOrder(UUID driverId, UUID taxiRideId) {
        TaxiRideEntity taxiRide = taxiRideRepository.findById(taxiRideId).orElseThrow(TaxiRideNotFoundException::new);
        DriverEntity driver = driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        if (!taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.DRIVER_SEARCH))
            throw new TaxiRideException("Cannot take order, because order status is not driver search");
        if (DriverStatusUtil.canWork(driver)) {
            if (driver.getDriverStatus().equals(DriverStatus.NOT_AT_WORK))
                throw new DriverNotAtWorkException("Driver not at work, cannot take order");
            if (userTaxiRideService.driverHasUnfinishedTrip(driverId)) throw new DriverHasUnfinishedTripException();
            CarEntity car = CarUsingUtil.getCurrentCar(driver);
            if (car == null) throw new CarUsingException("Driver cannot take order without car");
            if (!car.getCarClass().equals(taxiRide.getCarClass())) throw new CarClassNotAppropriateException();
            if (driverId.equals(taxiRide.getPassenger().getUuid())) throw new DriverAndPassengerSameException();
            taxiRide.setDriver(driver);
            taxiRide.setCar(car);
            taxiRide.setTaxiRideStatus(TaxiRideStatus.WAITING_FOR_DRIVER_ARRIVING);
            taxiRideRepository.save(taxiRide);
        } else if (!DriverStatusUtil.accountVerified(driver))
            throw new VerifiedException("Account not verified, cannot take order");
        else throw new DriverHasNoContractException("Driver does not have a contract, cannot take order");
    }

    @Override
    public List<DriverResponse> findAllFreeDrivers(TaxiRideEntity taxiRide) {
        List<DriverEntity> drivers = driverRepository.findAllByDriverStatus(DriverStatus.AT_WORK);
        return drivers.stream().filter(driver -> driver.getCurrentLocation() != null && !userTaxiRideService.driverHasUnfinishedTrip(driver.getUuid())
                        && carFitsCall(CarUsingUtil.getCurrentCar(driver), taxiRide))
                .map(driver -> driverService.getDriverById(driver.getUuid())).collect(Collectors.toList());
    }

    @Override
    public List<DriverResponse> findAllFreeDrivers(UpcomingTaxiCallRequest taxiCallRequest) {
        List<DriverEntity> drivers = driverRepository.findAllByDriverStatus(DriverStatus.AT_WORK);
        return drivers.stream().filter(driver -> driver.getCurrentLocation() != null && !userTaxiRideService.driverHasUnfinishedTrip(driver.getUuid())
                        && carFitsCall(CarUsingUtil.getCurrentCar(driver), taxiCallRequest))
                .map(driver -> driverService.getDriverById(driver.getUuid())).collect(Collectors.toList());
    }

    @Override
    public boolean carFitsCall(CarEntity car, TaxiRideEntity taxiRide) {
        if (car == null) return false;
        else if (!car.getCarClass().equals(taxiRide.getCarClass())) return false;
        else if (taxiRide.getAdditionalRequirements() == null) return true;
        else if (taxiRide.getAdditionalRequirements().getSeatsNumber() != null && taxiRide.getAdditionalRequirements().getSeatsNumber() > car.getSeatsNumber())
            return false;
        else if (taxiRide.getAdditionalRequirements().getChildSeatsNumber() != null && taxiRide.getAdditionalRequirements().getChildSeatsNumber() > car.getChildSeatsNumber())
            return false;
        else return true;
    }

    @Override
    public boolean carFitsCall(CarEntity car, UpcomingTaxiCallRequest taxiCallRequest) {
        if (car == null) return false;
        else if (!car.getCarClass().getUuid().equals(taxiCallRequest.getCarClassId())) return false;
        else if (taxiCallRequest.getAdditionalRequirements() == null) return true;
        else if (taxiCallRequest.getAdditionalRequirements().getSeatsNumber() != null && taxiCallRequest.getAdditionalRequirements().getSeatsNumber() > car.getSeatsNumber())
            return false;
        else if (taxiCallRequest.getAdditionalRequirements().getChildSeatsNumber() != null && taxiCallRequest.getAdditionalRequirements().getChildSeatsNumber() > car.getChildSeatsNumber())
            return false;
        else return true;
    }

    @Override
    public TaxiRideEntity getTaxiRideEntityById(UUID taxiRideId) {
        return taxiRideRepository.findById(taxiRideId).orElseThrow(TaxiRideNotFoundException::new);
    }

    @Override
    public void save(TaxiRideEntity taxiRide) {
        taxiRideRepository.save(taxiRide);
    }

    @Override
    public UUID arriveToClient(UUID driverId) {
        driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        TaxiRideEntity taxiRide = userTaxiRideService.getCurrentTaxiRideForDriver(driverId);
        if (taxiRide == null)
            throw new DriverHasNotCurrentTripException("Driver has not a current trip, it is impossible to arrive to client");
        if (!taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.WAITING_FOR_DRIVER_ARRIVING))
            throw new TaxiRideException("Cannot arrive to client, because order status is not waiting for driver arriving");
        taxiRide.setArrivingTime(Instant.now());
        taxiRide.setTaxiRideStatus(TaxiRideStatus.WAITING_FOR_CLIENT);
        taxiRideRepository.save(taxiRide);
        return taxiRide.getUuid();
    }

    @Override
    public UUID startTrip(UUID driverId) {
        driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        TaxiRideEntity taxiRide = userTaxiRideService.getCurrentTaxiRideForDriver(driverId);
        if (taxiRide == null)
            throw new DriverHasNotCurrentTripException("Driver has not a current trip, it is impossible to start trip");
        if (!taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.WAITING_FOR_CLIENT))
            throw new TaxiRideException("Cannot start trip, because order status is not waiting for client");
        taxiRide.setTripStartTime(Instant.now());
        calculatePriceForWaitingClient(taxiRide);
        taxiRide.setTaxiRideStatus(TaxiRideStatus.TRIP_IN_PROGRESS);
        taxiRideRepository.save(taxiRide);
        return taxiRide.getUuid();
    }

    private void calculatePriceForWaitingClient(TaxiRideEntity taxiRide) {
        long waitingTime = taxiRide.getTripStartTime().getEpochSecond() - taxiRide.getArrivingTime().getEpochSecond();
        waitingTime /= 60;
        if (waitingTime > taxiRide.getCarClass().getFreeWaitingMinutes()) {
            taxiRide.setTripPrice((int) (taxiRide.getTripPrice() + (waitingTime - taxiRide.getCarClass().getFreeWaitingMinutes())
                    * taxiRide.getCarClass().getPaidWaitingPriceForMinute()));
        }
    }

    @Override
    public UUID cancelTrip(UUID driverId) {
        driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        TaxiRideEntity taxiRide = userTaxiRideService.getCurrentTaxiRideForDriver(driverId);
        if (taxiRide == null) throw new CancelTripException("Driver does not have an active trip");
        if (!TaxiRideStatusUtil.cancellableByDriver(taxiRide))
            throw new CancelTripException("Driver cannot cancel trip in progress or before arriving to passenger");
        taxiRide.setTaxiRideStatus(TaxiRideStatus.TRIP_CANCELED_BY_DRIVER);
        taxiRideRepository.save(taxiRide);
        return taxiRide.getUuid();
    }

    @Override
    public UUID finishTrip(UUID driverId) {
        driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
        TaxiRideEntity taxiRide = userTaxiRideService.getCurrentTaxiRideForDriver(driverId);
        if (taxiRide == null)
            throw new DriverHasNotCurrentTripException("Driver has not a current trip, it is impossible to finish trip");
        if (!taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.TRIP_IN_PROGRESS))
            throw new TaxiRideException("Cannot finish trip, because order status is not trip in progress");
        taxiRide.setTripFinishTime(Instant.now());
        taxiRide.setTaxiRideStatus(TaxiRideStatus.TRIP_IS_OVER);
        taxiRideRepository.save(taxiRide);
        return taxiRide.getUuid();
    }

}
