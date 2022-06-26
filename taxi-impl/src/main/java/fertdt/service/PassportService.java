package fertdt.service;

import fertdt.dto.request.PassportRequest;
import fertdt.dto.response.PassportResponse;

import java.util.UUID;

public interface PassportService {
    UUID addPassport(PassportRequest passport);

    PassportResponse getPassportById(UUID passportId);

    void deletePassport(UUID passportId);
}
