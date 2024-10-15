package com.mcug.minichat.entrance.mapper;

import com.mcug.minichat.entrance.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertUser(User user);

    int isUserExist(String username);

    User findUserByUsername(String username);
}
