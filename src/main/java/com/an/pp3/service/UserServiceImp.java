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

    @Transactional(readOnly = false)
    @Override
    public User saveUser(User user) {
        user.setRecordDateTime(new Date());
        return userDao.save(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void saveUsers(List<User> users) {
        users.forEach(u -> u.setRecordDateTime(new Date()));
        userDao.saveAllAndFlush(users);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
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
        return userDao.findAll(Sort.by(sortDirection, column));
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getReferenceById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void removeUser(User user) {
        userDao.delete(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void removeUserById(Long id) {
        userDao.deleteById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void removeAllUsers() {
        userDao.deleteAll();
    }
}
