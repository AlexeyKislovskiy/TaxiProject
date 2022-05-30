package fertdt.controller;

import fertdt.api.ComplaintApi;
import fertdt.dto.request.ComplaintRequest;
import fertdt.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ComplaintController implements ComplaintApi {
    private final ComplaintService complaintService;

    @Override
    public void sendComplaint(ComplaintRequest complaintRequest) {
        complaintService.sendComplaint(complaintRequest);
    }
}
