package com.mcug.minichat.chatProcessService;

import com.mcug.minichat.chatProcessService.dto.SendDTO;
import com.mcug.minichat.utils.ApiResponse;
import com.mcug.minichat.utils.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/latest")
    public ResponseEntity<ApiResponse> getLatestChat(@RequestParam("userId") String userId) {
        List<ChatMessage> chatList = chatService.getUserLatestMessage(userId);
        return ResponseEntity.ok(ApiResponse.success("success", chatList));
    }
}
