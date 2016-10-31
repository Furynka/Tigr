package com.dreamteam.dao;

import com.dreamteam.entity.Animal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Access class for Animal entities.
 * @author Jiri Oliva
 */
@Repository
@Transactional
public class AnimalDaoImpl implements AnimalDao{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void create(Animal animal) {
        entityManager.persist(animal);
    }

    @Override
    public void delete(Animal animal) {
        entityManager.remove(findById(animal.getId()));
    }

    @Override
    public void update(Animal animal) {
        entityManager.merge(animal);
    }

    @Override
    public Animal findById(Long id) {
        return entityManager.find(Animal.class, id);
    }

    @Override
    public Animal findByName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Received invalid name");

        try {
            return entityManager.createQuery(
                    "select a from Animal a where a.name=:name", Animal.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Animal> getAll() {
        TypedQuery<Animal> query = entityManager.createQuery(
                "SELECT a FROM Animal a", Animal.class);
        return query.getResultList();
    }
    
}
