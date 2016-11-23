
package com.dreamteam.facade;

import com.dreamteam.dto.AnimalDTO;
import java.util.List;

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
     * @param animal Animal to remove
     */
    public void deleteAnimal(AnimalDTO animal);
    
    /**
     * Updates existing Animal entity;
     * @param animal Animal to update
     * @param newName new name
     */
    public void changeAnimalName(AnimalDTO animal, String newName);
    
    /**
     * Updates existing Animal entity;
     * @param animal Animal to update
     * @param description new description 
     */
    public void changeAnimalDescription(AnimalDTO animal, String description);
    
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
