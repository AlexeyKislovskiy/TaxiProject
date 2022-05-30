package fertdt.controller;

import fertdt.api.ChatApi;
import fertdt.dto.request.ChatMessageRequest;
import fertdt.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController implements ChatApi {
    private final ChatService chatService;

    @Override
    public void sendMessage(ChatMessageRequest chatMessageRequest) {
        chatService.sendMessage(chatMessageRequest);
    }
}
