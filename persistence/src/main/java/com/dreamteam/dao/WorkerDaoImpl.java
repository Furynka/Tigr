package com.dreamteam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.dreamteam.entity.Worker;

@Repository
public class WorkerDaoImpl implements WorkerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Worker w) {
        em.persist(w);
    }

    @Override
    public Worker findWorkerByEmail(String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("Cannot search for null e-mail");

        try {
            return em
                    .createQuery("select w from Worker w where email=:email",
                            Worker.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Worker findById(Long id) {
        return em.find(Worker.class, id);
    }

    @Override
    public List<Worker> all() {
        TypedQuery<Worker> query = em.createQuery("SELECT w FROM Worker w",
                Worker.class);
        return (List<Worker>) query.getResultList();
    }

}
