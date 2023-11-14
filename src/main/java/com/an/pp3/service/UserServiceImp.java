package com.an.pp3.service;

import com.an.pp3.model.User;
import com.an.pp3.dao.UserDao;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        if (user != null) {
            user.setRecordDateTime(new Date());
            userDao.saveUser(user);
        }
    }

    @Transactional
    @Override
    public void saveUsers(List<User> users) {
        users.forEach(u -> u.setRecordDateTime(new Date()));
        userDao.saveUsers(users);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<User> getAllUsersSorted(String column, String direction) {
        if (column.equals("id")) {
            return getAllUsers();
        }
        Sort.Direction sortDirection;
        try {
            sortDirection = Sort.Direction.fromString(direction);
        } catch (IllegalArgumentException e) {
            sortDirection = Sort.Direction.ASC;
        }
        return userDao.getAllUsersSorted(column, sortDirection.name());
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        if (user != null) {
            user.setRecordDateTime(new Date());
            userDao.updateUser(user);
        }
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
    }

    @Transactional
    @Override
    public void removeAllUsers() {
        userDao.removeAllUsers();
    }
}
