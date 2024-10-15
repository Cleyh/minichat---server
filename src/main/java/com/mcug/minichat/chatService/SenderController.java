package com.mcug.minichat.chatService;

import com.mcug.minichat.chatService.dto.SendDTO;
import com.mcug.minichat.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class SenderController {
    @Autowired
    private SenderService senderService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> send(@RequestAttribute("user_id") String userId,
                                            @RequestBody SendDTO message) {
        senderService.sendChatMessage(userId, message);
        return ResponseEntity.ok(ApiResponse.success("success", ""));
    }
}
