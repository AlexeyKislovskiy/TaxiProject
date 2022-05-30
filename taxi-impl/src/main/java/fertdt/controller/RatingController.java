package fertdt.controller;

import fertdt.api.RatingApi;
import fertdt.dto.request.RatingRequest;
import fertdt.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RatingController implements RatingApi {
    private final RatingService ratingService;

    @Override
    public void putRating(RatingRequest ratingRequest) {
        ratingService.putRating(ratingRequest);
    }
}
