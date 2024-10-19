package com.mcug.minichat.chatInfo;

import com.mcug.minichat.utils.ApiResponse;
import com.mcug.minichat.utils.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatInfoController {
    @Autowired
    ChatInfoService chatInfoService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<User> userList = chatInfoService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success("success", userList));
    }
}
