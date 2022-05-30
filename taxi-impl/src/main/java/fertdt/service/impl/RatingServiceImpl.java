package fertdt.service.impl;

import fertdt.dto.request.RatingRequest;
import fertdt.exception.ComplaintException;
import fertdt.exception.RatingException;
import fertdt.exception.duplicatedName.DuplicatedRatingException;
import fertdt.model.RatingEntity;
import fertdt.model.TaxiRideEntity;
import fertdt.model.TaxiRideStatus;
import fertdt.repository.RatingRepository;
import fertdt.service.RatingService;
import fertdt.service.TaxiRideService;
import fertdt.service.UserService;
import fertdt.util.mapper.RatingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final TaxiRideService taxiRideService;
    private final UserService userService;

    @Override
    public void putRating(RatingRequest ratingRequest) {
        TaxiRideEntity taxiRide = taxiRideService.getTaxiRideEntityById(ratingRequest.getTaxiRideId());
        if (!taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.TRIP_IS_OVER))
            throw new RatingException("It is impossible to put a rating before the end of the trip or if trip was cancelled");
        userService.getUserById(ratingRequest.getUserId());
        if (!ratingRequest.getUserId().equals(taxiRide.getPassenger().getUuid()) && !ratingRequest.getUserId().equals(taxiRide.getDriver().getUuid()))
            throw new ComplaintException("Cannot put a rating if you haven't been on this taxi ride");
        RatingEntity ratingEntity = ratingMapper.toEntity(ratingRequest, taxiRide);
        if (ratingRepository.findByTaxiRideAndStatus(taxiRide, ratingEntity.getStatus()).isPresent())
            throw new DuplicatedRatingException();
        ratingRepository.save(ratingEntity);
    }
}
