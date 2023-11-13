package com.an.pp3.service;

import com.an.pp3.model.User;
import com.an.pp3.dao.UserDao;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        user.setRecordDateTime(new Date());
        return userDao.save(user);
    }

    @Transactional
    @Override
    public void saveUsers(List<User> users) {
        users.forEach(u -> u.setRecordDateTime(new Date()));
        userDao.saveAllAndFlush(users);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Transactional
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
        return userDao.findAll(Sort.by(sortDirection, column));
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userDao.getReferenceById(id);
    }

    @Transactional
    @Override
    public void removeUser(User user) {
        userDao.delete(user);
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        userDao.deleteById(id);
    }

    @Transactional
    @Override
    public void removeAllUsers() {
        userDao.deleteAll();
    }
}
