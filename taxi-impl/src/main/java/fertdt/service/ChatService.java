package fertdt.service;

import fertdt.dto.request.ChatMessageRequest;

public interface ChatService {
    void sendMessage(ChatMessageRequest chatMessageRequest);
}
