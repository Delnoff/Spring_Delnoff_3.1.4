package com.example.springhtmldelnoff.dao;

import com.example.springhtmldelnoff.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RoleDaoImpl implements RoleDao {

    private final EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean add(Role user) {
        entityManager.persist(user);
        return true;
    }


    public Set<Role> listRoles() {
        List<Role> query = entityManager.createQuery("from Role", Role.class).getResultList();
        return new HashSet<>(query);
    }

    public Role findByName(String name) {
        return entityManager.createQuery("select u from Role u where u.role = :id", Role.class)
                .setParameter("id", name)
                .getResultList().stream().findAny().orElse(null);
    }


}
