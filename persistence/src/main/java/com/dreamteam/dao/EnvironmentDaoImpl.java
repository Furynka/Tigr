package com.dreamteam.dao;

import com.dreamteam.entity.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Eva Ambrusova
 */
@Repository
@Transactional
public class EnvironmentDaoImpl implements EnvironmentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Environment e) {
        em.persist(e);
    }

    @Override
    public Environment findById(int id) {
        return em.find(Environment.class, id);
    }

    @Override
    public Environment findByName(String name) {
        try{
            return em.createQuery("SELECT e FROM Environment e WHERE name=:name", Environment.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public List<Environment> findAll() {
        return em.createQuery("SELECT e FROM Environment e", Environment.class).getResultList();
    }

    @Override
    public Environment update(Environment e) {
        return em.merge(e);
    }

    @Override
    public void delete(Environment e) {
        em.remove(findById(e.getEnvironmentId()));
    }
}
