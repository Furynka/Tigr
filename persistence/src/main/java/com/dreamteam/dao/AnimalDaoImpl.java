package com.dreamteam.dao;

import com.dreamteam.entity.Animal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Access class for Animal entities.
 * @author Jiri Oliva
 */
@Repository
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
    public Animal update(Animal animal) {
        return entityManager.merge(animal);
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
