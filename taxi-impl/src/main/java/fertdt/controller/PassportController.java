package fertdt.controller;

import fertdt.api.PassportApi;
import fertdt.dto.request.PassportRequest;
import fertdt.dto.response.PassportResponse;
import fertdt.service.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PassportController implements PassportApi {
    private final PassportService passportService;

    @Override
    public UUID addPassport(PassportRequest passport) {
        return passportService.addPassport(passport);
    }

    @Override
    public PassportResponse getPassport(UUID passportId) {
        return passportService.getPassportById(passportId);
    }

    @Override
    public void deletePassport(UUID passportId) {
        passportService.deletePassport(passportId);
    }
}
