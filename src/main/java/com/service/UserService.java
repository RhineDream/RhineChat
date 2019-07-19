package com.service;

import com.model.User;

import java.util.List;
/**
 * author：RhineDream
 */
public interface UserService {

    User getUserByName(String username);

    List<User> getUserList(User user);

    void insert(User user);
}
