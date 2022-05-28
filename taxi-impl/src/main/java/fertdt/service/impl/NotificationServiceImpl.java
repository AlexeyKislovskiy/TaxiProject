package fertdt.service.impl;

import fertdt.dto.request.NearestDriversRequest;
import fertdt.dto.response.TaxiRideResponse;
import fertdt.exception.ApiException;
import fertdt.model.DriverEntity;
import fertdt.model.TaxiRideEntity;
import fertdt.model.TaxiRideStatus;
import fertdt.service.DriverService;
import fertdt.service.NavigationService;
import fertdt.service.NotificationService;
import fertdt.service.TaxiRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final TaxiRideService taxiRideService;
    private final NavigationService navigationService;
    private final SimpMessagingTemplate template;
    private final DriverService driverService;

    @Override
    public void notifyDriversAboutNewCall(UUID taxiRideId) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            int limit = 1;

            @Override
            public void run() {
                TaxiRideEntity taxiRide = taxiRideService.getTaxiRideEntityById(taxiRideId);
                TaxiRideResponse taxiRideResponse = taxiRideService.getTaxiRideById(taxiRideId);
                if (taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.DRIVER_SEARCH)) {
                    NearestDriversRequest nearestDriversRequest = NearestDriversRequest.builder()
                            .taxiRideId(taxiRideId)
                            .limit(limit)
                            .build();
                    Set<DriverEntity> drivers = navigationService.findNearestDrivers(nearestDriversRequest).stream()
                            .map(driver -> driverService.getDriverEntityById(driver.getId())).collect(Collectors.toSet());
                    for (DriverEntity driver : drivers) {
                        if (!taxiRide.getNotifications().contains(driver)) {
                            template.convertAndSend("/notification/drivers/" + driver.getUuid(), taxiRideResponse);
                            taxiRide.getNotifications().add(driver);
                            taxiRideService.save(taxiRide);
                        }
                    }
                    limit += 2;
                } else throw new ApiException();
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void notifyDriversAboutStopCall(UUID taxiRideId) {
        TaxiRideEntity taxiRide = taxiRideService.getTaxiRideEntityById(taxiRideId);
        TaxiRideResponse taxiRideResponse = taxiRideService.getTaxiRideById(taxiRideId);
        Set<DriverEntity> drivers = taxiRide.getNotifications();
        for (DriverEntity driver : drivers) {
            template.convertAndSend("/notification/drivers/" + driver.getUuid(), taxiRideResponse);
        }
        drivers.removeIf(driver -> !driver.equals(taxiRide.getDriver()));
        taxiRideService.save(taxiRide);
    }

    @Override
    public void notifyPassengerAboutChangeCallStatus(UUID taxiRideId) {
        TaxiRideEntity taxiRide = taxiRideService.getTaxiRideEntityById(taxiRideId);
        TaxiRideResponse taxiRideResponse = taxiRideService.getTaxiRideById(taxiRideId);
        template.convertAndSend("/notification/passengers/" + taxiRide.getPassenger().getUuid(), taxiRideResponse);
    }
}
