
package com.dreamteam.service;

import com.dreamteam.dao.AnimalDao;
import com.dreamteam.entity.Animal;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *  Implementation class for {@link AnimalService}
 *  
 * @author Jiri Oliva
 */
@Service
public class AnimalServiceImpl implements AnimalService{

    @Inject
    private AnimalDao animalDao;
        
    @Override
    public void create(Animal animal) {
        animalDao.create(animal);
    }

    @Override
    public void delete(Animal animal) {
        animalDao.delete(animal);
    }

    @Override
    public Animal update(Animal animal) {
        return animalDao.update(animal);
    }

    @Override
    public Animal findById(Long id) {
        return animalDao.findById(id);
    }

    @Override
    public Animal findByName(String name) {
        return animalDao.findByName(name);
    }

    @Override
    public List<Animal> getAll() {
        return animalDao.getAll();
    }
    
}
