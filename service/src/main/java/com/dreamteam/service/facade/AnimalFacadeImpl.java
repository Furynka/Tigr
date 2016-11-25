
package com.dreamteam.service.facade;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.facade.AnimalFacade;
import com.dreamteam.service.config.ServiceConfig;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * Implementation class for Animal facade layer. 
 * 
 * @author Jiri Oliva
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class AnimalFacadeImpl implements AnimalFacade{

    @Override
    public void createAnimal(AnimalDTO animal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAnimal(AnimalDTO animal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeAnimalName(AnimalDTO animal, String newName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeAnimalDescription(AnimalDTO animal, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnimalDTO findAnimalById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnimalDTO findAnimalByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnimalDTO> getAllAnimals() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
