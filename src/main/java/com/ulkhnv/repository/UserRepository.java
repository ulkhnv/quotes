package com.ulkhnv.repository;

import com.ulkhnv.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void addUser(User user) {
        entityManager.persist(user);
    }

    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }
}
