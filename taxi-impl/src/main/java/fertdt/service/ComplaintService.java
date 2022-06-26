package fertdt.service;

import fertdt.dto.request.ComplaintRequest;

public interface ComplaintService {
    void sendComplaint(ComplaintRequest complaintRequest);
}
