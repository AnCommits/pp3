package com.an.pp3.service;

import com.an.pp3.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void saveUsers(List<User> users);

    User getUserById(Long id);

    List<User> getAllUsers();

    List<User> getAllUsersSorted(String column, String direction);

    void updateUser(User user);

    void removeUserById(Long id);

    void removeAllUsers();
}
