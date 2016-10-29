package com.dreamteam.dao;

import com.dreamteam.entity.Animal;
import java.util.List;

/**
 * Access interface for Animal entities.
 * 
 * @author Jiri Oliva
 */
public interface AnimalDao {
    
    /**
     * Creates new Animal entity.
     * @param animal new Animal entity
     */
    public void create(Animal animal);
    
    /**
     * Deletes existing Animal entity.
     * @param animal Animal entity to remove
     */
    public void delete(Animal animal);
    
    /**
     * Updates existing Animal entity;
     * @param animal Animal entity to update
     */
    public void update(Animal animal);
    
    /**
     * Finds Animal entity by its Id.
     * @param id
     * @return Animal or null, if not existing
     */
    public Animal findById(Long id);
    
    /**
     * Finds Animal entity by its name.
     * @param name
     * @return Animal or null, if not existing
     */
    public Animal findByName(String name);
    
    /**
     * Returns all existing Animal entities.
     * @return List filled with existing Animal entities.
     */
    public List<Animal> getAll();
}
