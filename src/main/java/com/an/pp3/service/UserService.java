package com.an.pp3.service;

import com.an.pp3.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    void saveUsers(List<User> users);

    List<User> getAllUsers();

    List<User> getAllUsersSorted(String column, String direction);

    User getUserById(Long id);

    void removeUser(User user);

    void removeUserById(Long id);

    void removeAllUsers();
}
