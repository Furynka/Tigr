
package com.dreamteam.service;

import com.dreamteam.dao.AnimalDao;
import com.dreamteam.dao.EnvironmentDao;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *  Implementation class for {@link AnimalService}
 *  
 * @author Jiri Oliva
 */
@Service
public class AnimalServiceImpl implements AnimalService{

    @Inject
    private AnimalDao animalDao;

    @Inject
    private EnvironmentDao envDao;
        
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

    /**
     * Searches for 5 most endangered animals in specified environment.
     * @param env is environment searched for endangered species
     * @return List of five most endangered animals.
     */
//    @Override
//    public List<Animal> getTopFiveEndangeredAnimals(Environment env){
//
//        System.out.println("Hello world");
//
//        List<Animal> allAnimals = (envDao.findByName(env.getName())).getAnimals();
//
//        if (allAnimals == null){
//            return null;
//        }
//
//
//     //   for (Animal animal : allAnimals){
//     //   }
//
//
//        return allAnimals;
//    }
    
}
