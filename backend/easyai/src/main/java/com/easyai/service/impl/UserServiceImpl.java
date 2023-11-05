package com.easyai.service.impl;

import com.easyai.bean.User;
import com.easyai.mapper.UserMapper;
import com.easyai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> GetUserByUserId(String userId) {
        return userMapper.GetUserByUserId(userId);
    }

    @Override
    public int InsertUser(User user) {
        return userMapper.InsertUser(user);
    }
}