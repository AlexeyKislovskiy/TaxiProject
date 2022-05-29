package fertdt.service.impl;

import fertdt.model.TaxiRideEntity;
import fertdt.repository.TaxiRideRepository;
import fertdt.service.UserTaxiRideService;
import fertdt.util.TaxiRideStatusUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserTaxiRideServiceImpl implements UserTaxiRideService {
    private final TaxiRideRepository taxiRideRepository;

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

    @Override
    public TaxiRideEntity getCurrentTaxiRideForDriver(UUID driverId) {
        Set<TaxiRideEntity> taxiRides = taxiRideRepository.findAllByDriver_Uuid(driverId);
        for (TaxiRideEntity taxiRide : taxiRides) {
            if (!TaxiRideStatusUtil.tripIsOver(taxiRide)) return taxiRide;
        }
        return null;
    }

    @Override
    public boolean driverHasUnfinishedTrip(UUID driverId) {
        return getCurrentTaxiRideForDriver(driverId) != null;
    }
}
