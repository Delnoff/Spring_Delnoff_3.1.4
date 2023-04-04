package com.example.springhtmldelnoff.dao;

import com.example.springhtmldelnoff.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User findByName(String email) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList().stream().findAny().orElse(null);
    }

    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public boolean add(User user) {
        entityManager.persist(user);
        return true;
    }

    public List<User> listUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
}

