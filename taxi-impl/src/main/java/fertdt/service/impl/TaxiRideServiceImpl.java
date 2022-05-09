package fertdt.service.impl;

import fertdt.dto.request.TaxiCallRequest;
import fertdt.dto.request.UpcomingTaxiCallRequest;
import fertdt.dto.response.TaxiRideResponse;
import fertdt.exception.deletion.CancelTaxiCallException;
import fertdt.exception.notFound.TaxiRideNotFoundException;
import fertdt.exception.relationalshipConflict.GeographicalPointsConflictException;
import fertdt.exception.relationalshipConflict.UserHasUnfinishedTripException;
import fertdt.model.GeographicalPointEntity;
import fertdt.model.TaxiRideEntity;
import fertdt.model.TaxiRideStatus;
import fertdt.repository.TaxiRideRepository;
import fertdt.service.*;
import fertdt.util.TaxiRideStatusUtil;
import fertdt.util.mapper.TaxiRideMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaxiRideServiceImpl implements TaxiRideService {
    private final TaxiRideRepository taxiRideRepository;
    private final TaxiRideMapper taxiRideMapper;
    private final UserService userService;
    private final CarCLassService carCLassService;
    private final PaymentMethodService paymentMethodService;
    private final GeographicalPointService geographicalPointService;
    private final AdditionalRequirementsService additionalRequirementsService;

    @Override
    public UUID callTaxi(TaxiCallRequest taxiCallRequest) {
        UpcomingTaxiCallRequest taxiCall = taxiCallRequest.getTaxiCall();
        userService.getUserById(taxiCall.getPassengerId());
        carCLassService.getClassCarById(taxiCall.getCarClassId());
        paymentMethodService.getPaymentMethodById(taxiCall.getPaymentMethodId());
        if (userHasUnfinishedTrip(taxiCall.getPassengerId())) throw new UserHasUnfinishedTripException();
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
    public void cancelTaxiCall(UUID userId) {
        userService.getUserById(userId);
        TaxiRideEntity taxiRide = getCurrentTaxiRideForUser(userId);
        if (taxiRide == null) throw new CancelTaxiCallException("User does not have an active trip");
        if (!TaxiRideStatusUtil.cancellableByPassenger(taxiRide))
            throw new CancelTaxiCallException("User cannot cancel trip in progress");
        taxiRide.setTaxiRideStatus(TaxiRideStatus.TRIP_CANCELED_BY_CLIENT);
        taxiRideRepository.save(taxiRide);
    }

    @Override
    public TaxiRideResponse getTaxiRideById(UUID taxiRideId) {
        return taxiRideMapper.toResponse(
                taxiRideRepository.findById(taxiRideId).orElseThrow(TaxiRideNotFoundException::new)
        );
    }

    @Override
    public TaxiRideEntity getCurrentTaxiRideForUser(UUID userId) {
        Set<TaxiRideEntity> taxiRides = taxiRideRepository.findAllByPassenger_Uuid(userId);
        for (TaxiRideEntity taxiRide : taxiRides) {
            if (!TaxiRideStatusUtil.tripIsOver(taxiRide)) return taxiRide;
        }
        return null;
    }

    @Override
    public boolean userHasUnfinishedTrip(UUID userId) {
        return getCurrentTaxiRideForUser(userId) != null;
    }
}
