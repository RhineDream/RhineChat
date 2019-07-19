package com.service;

import com.model.User;

import java.util.List;

public interface UserService {

    User getUserByName(String username);

    List<User> getUserList(User user);

    void insert(User user);
}
