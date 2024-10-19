package com.mcug.minichat.entrance.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {
    private String userName;
    private String password;
}
