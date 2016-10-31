package com.dreamteam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.dreamteam.entity.Worker;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class WorkerDaoImpl implements WorkerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Worker w) {
        entityManager.persist(w);
    }

    @Override
    public void update(Worker w) {
        entityManager.merge(w);
    }

    @Override
    public void delete(Worker w) {
        entityManager.remove(w);
    }

    @Override
    public Worker findWorkerByEmail(String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("Cannot search for null e-mail");

        try {
            return entityManager
                    .createQuery("select w from Worker w where email=:email",
                            Worker.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Worker findById(Long id) {
        return entityManager.find(Worker.class, id);
    }

    @Override
    public List<Worker> all() {
        TypedQuery<Worker> query = entityManager.createQuery("SELECT w FROM Worker w",
                Worker.class);
        return (List<Worker>) query.getResultList();
    }

}
