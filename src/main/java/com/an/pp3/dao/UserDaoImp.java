package com.an.pp3.dao;

import com.an.pp3.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void saveUsers(List<User> users) {
        for (User user : users) {
            entityManager.persist(user);
        }
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        String hql = "FROM User";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        return query.getResultList();
    }

    @Override
    public List<User> getAllUsersSorted(String column, String direction) {
        String hql = "FROM User u ORDER BY u." + column + " " + direction;
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        return query.getResultList();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void removeAllUsers() {
        String sql = "truncate table users";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }
}
