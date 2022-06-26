package fertdt.api;

import fertdt.dto.request.ChatMessageRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;

public interface ChatApi {
    @MessageMapping("/chat")
    void sendMessage(ChatMessageRequest chatMessageRequest);
}
