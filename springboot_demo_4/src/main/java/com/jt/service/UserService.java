package com.jt.service;

import com.jt.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(Integer id);
}
