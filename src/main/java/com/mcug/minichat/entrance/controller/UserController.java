package com.mcug.minichat.entrance.controller;

import com.mcug.minichat.entrance.dto.LoginDTO;
import com.mcug.minichat.entrance.dto.RegisterDTO;
import com.mcug.minichat.entrance.entity.User;
import com.mcug.minichat.entrance.message.EntranceEventService;
import com.mcug.minichat.entrance.service.UserService;
import com.mcug.minichat.utils.ApiResponse;
import com.mcug.minichat.utils.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
 * 用户登录注册接口
 * */
@RestController
@RequestMapping("/entrance")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    EntranceEventService entranceEventService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterDTO registerDTO) {
        if (userService.isUserExist(registerDTO.getUsername()))
            return ResponseEntity.badRequest().body(ApiResponse.error("用户名已存在", null));
        UUID uuid = userService.register(registerDTO);
        return ResponseEntity.ok(ApiResponse.success("注册成功", uuid));
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO);
        if (user == null)
            return ResponseEntity.badRequest().body(ApiResponse.error("用户名或密码错误", null));
        user.setUserPassword(null);
        String token = TokenService.generateToken(String.valueOf(user.getUserId()));
        entranceEventService.userReadyConnect(token);
        return ResponseEntity.ok(ApiResponse.success(token, user));
    }
}
