package com.easyai.service;

import com.easyai.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    List<User> GetUserByUserId(String userId);
    int InsertUser(User user);
}