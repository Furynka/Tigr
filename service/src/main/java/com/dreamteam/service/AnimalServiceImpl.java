
package com.dreamteam.service;

import com.dreamteam.dao.AnimalDao;
import com.dreamteam.dao.EnvironmentDao;
import com.dreamteam.entity.Animal;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Animal> getTopOfFoodChain() {
        List<Animal> topChain = animalDao.getAll().stream().filter(animal -> animal.getPredators().size() == 0).collect(Collectors.toList());
        topChain.sort((a1, a2) -> a2.getPreys().size()-a1.getPreys().size());
        return topChain;
    }

}
