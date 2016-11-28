package com.dreamteam.dao;

import com.dreamteam.entity.Species;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan.novak
 */
@Repository
@Transactional
public class SpeciesDaoImpl implements SpeciesDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Species s) {
        entityManager.persist(s);
    }

    @Override
    public void update(Species s) {
        entityManager.merge(s);
    }

    @Override
    public void delete(Species s) {
        entityManager.remove(findById(s.getId()));
    }

    @Override
    public Species findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Cannot search for null id");

        try {
            return entityManager.find(Species.class, id);
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Species> all() {
        List<Species> resultList = entityManager.createQuery("SELECT s FROM Species s", Species.class).getResultList();
        return resultList != null ? resultList : new ArrayList<>();
    }

    @Override
    public List<Species> findAllInDanger() {
        List<Species> resultList = entityManager
                .createQuery("select s from Species s where inDanger=:inDanger", Species.class)
                .setParameter("inDanger", Boolean.TRUE).getResultList();
        return resultList != null ? resultList : new ArrayList<>();
    }
}
