package com.mcug.minichat.chatInfo;

import com.mcug.minichat.utils.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatInfoMapper {

    List<User> getAllUsers();
}
