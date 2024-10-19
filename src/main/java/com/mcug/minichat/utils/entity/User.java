package com.mcug.minichat.utils.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
public class User {
    String userId;
    String userName;
    String userPassword;

    public User(UUID userId, String userName, String userPassword) {
        this.userId = userId.toString();
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public User(String userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public User() {
    }
}
