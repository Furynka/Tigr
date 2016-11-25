
package com.dreamteam.facade;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.dto.EnvironmentDTO;
import com.dreamteam.dto.SpeciesDTO;
import java.util.List;
import java.util.Set;

/**
 *  Facade layer for Animal data.
 * 
 * @author Jiri Oliva
 */
public interface AnimalFacade {
    /**
     * Creates new Animal.
     * @param animal new Animal
     */
    public void createAnimal(AnimalDTO animal);
    
    /**
     * Deletes existing Animal.
     * @param id id of Animal to be deleted
     */
    public void deleteAnimal(long id);
    
    /**
     * Updates existing Animal entity;
     * @param id id of Animal to be updated
     * @param newName new name
     */
    public void changeAnimalName(long id, String newName);
    
    /**
     * Updates existing Animal entity;
     * @param id id of Animal to be updated
     * @param description new description 
     */
    public void changeAnimalDescription(long id, String description);
    
    /**
     * Updates existing Animal entity;
     * @param id id of Animal to be updated
     * @param species new species
     */
    public void changeAnimalSpecies(long id, SpeciesDTO species);
    
     /**
     * Updates existing Animal entity;
     * @param id id of Animal to be updated
     * @param predator new predator
     */
    public void addAnimalPredator(long id, AnimalDTO predator);
    
    /**
     * Updates existing Animal entity;
     * @param id id of Animal to be updated
     * @param prey new prey
     */
    public void addAnimalPrey(long id, AnimalDTO prey);
    
    /**
     * Updates existing Animal entity;
     * @param id id of Animal to be updated
     * @param environment new environment
     */
    public void addAnimalEnvironment(long id, EnvironmentDTO environment);
    
    /**
     * Updates existing Animal entity;
     * @param id id of Animal to be updated
     * @param predator predator to remove
     */
    public void removeAnimalPredator(long id, AnimalDTO predator);
    
    /**
     * Updates existing Animal entity;
     * @param id id of Animal to be updated
     * @param prey prey to remove
     */
    public void removeAnimalPrey(long id, AnimalDTO prey);
    
    /**
     * Updates existing Animal entity;
     * @param id id of Animal to be updated
     * @param environment environment to remove
     */
    public void removeAnimalEnvironment(long id, EnvironmentDTO environment);
    
    /**
     * Finds Animal by its Id.
     * @param id
     * @return Animal or null, if not existing
     */
    public AnimalDTO findAnimalById(Long id);
    
    /**
     * Finds Animal by its name.
     * @param name
     * @return Animal or null, if not existing
     */
    public AnimalDTO findAnimalByName(String name);
    
    /**
     * Returns all existing Animals.
     * @return List filled with existing Animals.
     */
    public List<AnimalDTO> getAllAnimals();
    
    
}
