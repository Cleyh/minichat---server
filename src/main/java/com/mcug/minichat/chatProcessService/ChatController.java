package com.mcug.minichat.chatProcessService;

import com.mcug.minichat.chatProcessService.dto.SendDTO;
import com.mcug.minichat.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/latest")
    public ResponseEntity<ApiResponse> getLatestChat(@RequestParam("userId") String userId) {
        return null;
    }


}
