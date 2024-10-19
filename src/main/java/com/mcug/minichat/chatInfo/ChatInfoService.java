package com.mcug.minichat.chatInfo;

import com.mcug.minichat.utils.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatInfoService {
    @Autowired
    ChatInfoMapper chatInfoMapper;


    public List<User> getAllUsers() {
        return chatInfoMapper.getAllUsers();
    }
}
