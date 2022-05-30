package fertdt.service.impl;

import fertdt.dto.request.ChatMessageRequest;
import fertdt.exception.ChatException;
import fertdt.model.TaxiRideEntity;
import fertdt.model.TaxiRideStatus;
import fertdt.service.ChatService;
import fertdt.service.UserService;
import fertdt.service.UserTaxiRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final UserTaxiRideService userTaxiRideService;
    private final UserService userService;
    private final SimpMessagingTemplate template;

    @Override
    public void sendMessage(ChatMessageRequest chatMessageRequest) {
        UUID userId = UUID.fromString(chatMessageRequest.getUserId());
        userService.getUserById(userId);
        TaxiRideEntity taxiRide = userTaxiRideService.getCurrentTaxiRideForUser(userId);
        if (taxiRide == null)
            taxiRide = userTaxiRideService.getCurrentTaxiRideForDriver(userId);
        if (taxiRide == null) throw new ChatException("Cannot send message because not taxi ride");
        if (taxiRide.getTaxiRideStatus().equals(TaxiRideStatus.DRIVER_SEARCH))
            throw new ChatException("Cannot send message while driver not found");
        chatMessageRequest.setTime(Instant.now().toString());
        template.convertAndSend("/chat/" + taxiRide.getPassenger().getUuid(), chatMessageRequest);
        template.convertAndSend("/chat/" + taxiRide.getDriver().getUuid(), chatMessageRequest);
    }
}
