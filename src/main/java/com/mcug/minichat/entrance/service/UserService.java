package com.mcug.minichat.entrance.service;

import com.mcug.minichat.entrance.dto.LoginDTO;
import com.mcug.minichat.entrance.dto.RegisterDTO;
import com.mcug.minichat.entrance.entity.User;
import com.mcug.minichat.entrance.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean isUserExist(String username) {
        return userMapper.isUserExist(username) == 1;
    }

    public UUID register(RegisterDTO registerDTO) {
        UUID userId = generateUUID(registerDTO.getUsername());
        User newUser = new User(userId, registerDTO.getUsername(), registerDTO.getPassword());
        userMapper.insertUser(newUser);
        return userId;
    }

    private UUID generateUUID(String username) {
        try {
            MessageDigest salt = MessageDigest.getInstance("SHA-256");
            salt.update(username.getBytes(StandardCharsets.UTF_8));
            byte[] digest = salt.digest();
            return UUID.nameUUIDFromBytes(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public User login(LoginDTO loginDTO) {
        User user = userMapper.findUserByUsername(loginDTO.getUsername());
        if (user != null && user.getUserPassword().equals(loginDTO.getPassword()))
            return user;
        return null;
    }
}
