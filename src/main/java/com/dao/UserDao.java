package com.dao;

import com.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * author：RhineDream
 */
@Mapper
public interface UserDao {

    User getUserByName(@Param("username") String username);

    void insert(User user);

    List<User> getUserList(User user);
}
