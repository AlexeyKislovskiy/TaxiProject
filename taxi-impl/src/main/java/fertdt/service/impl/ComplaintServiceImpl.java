package fertdt.service.impl;

import fertdt.dto.request.ComplaintRequest;
import fertdt.dto.response.UserResponse;
import fertdt.exception.ComplaintException;
import fertdt.model.ComplaintEntity;
import fertdt.model.TaxiRideEntity;
import fertdt.repository.ComplaintRepository;
import fertdt.service.ComplaintService;
import fertdt.service.TaxiRideService;
import fertdt.service.UserService;
import fertdt.util.TaxiRideStatusUtil;
import fertdt.util.mapper.ComplaintMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintRepository complaintRepository;
    private final ComplaintMapper complaintMapper;
    private final TaxiRideService taxiRideService;
    private final UserService userService;

    @Override
    public void sendComplaint(ComplaintRequest complaintRequest) {
        TaxiRideEntity taxiRide = taxiRideService.getTaxiRideEntityById(complaintRequest.getTaxiRideId());
        if (!TaxiRideStatusUtil.tripIsOver(taxiRide))
            throw new ComplaintException("It is impossible to send a complaint before the end of the trip");
        userService.getUserById(complaintRequest.getUserId());
        if (!complaintRequest.getUserId().equals(taxiRide.getPassenger().getUuid()) && !complaintRequest.getUserId().equals(taxiRide.getDriver().getUuid()))
            throw new ComplaintException("Cannot send a complaint if you haven't been on this taxi ride");
        ComplaintEntity complaintEntity = complaintMapper.toEntity(complaintRequest, taxiRide);
        complaintRepository.save(complaintEntity);
    }
}
