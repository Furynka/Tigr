package com.dreamteam.service;

import com.dreamteam.dao.AnimalDao;
import com.dreamteam.dao.EnvironmentDao;
import com.dreamteam.entity.Animal;
import com.dreamteam.exception.TigrServiceAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation class for {@link AnimalService}
 *
 * @author Jiri Oliva
 */
@Service
public class AnimalServiceImpl implements AnimalService {

    @Inject
    private AnimalDao animalDao;

    @Inject
    private EnvironmentDao envDao;

    @Override
    public void create(Animal animal) {
        checkNullObject(animal);
        try {
            animalDao.create(animal);
        } catch (Exception ex) {
            throw new TigrServiceAccessException("Cannot create Animal: " + animal);
        }
    }

    @Override
    public void delete(Animal animal) {
        checkNullObject(animal);
        try {
            animalDao.delete(animal);
        } catch (Exception ex) {
            throw new TigrServiceAccessException("Cannot delete Animal: " + animal);
        }

    }

    @Override
    public Animal update(Animal animal) {
        checkNullObject(animal);
        try {
            return animalDao.update(animal);
        } catch (Exception ex) {
            throw new TigrServiceAccessException("Cannot update Animal: " + animal);
        }

    }

    @Override
    public Animal findById(Long id) {
        checkNullObject(id);
        try {
            return animalDao.findById(id);
        } catch (Exception ex) {
            throw new TigrServiceAccessException("Cannot find Animal by Id: " + id);
        }

    }

    @Override
    public Animal findByName(String name) {
        checkNullObject(name);
        try {
            return animalDao.findByName(name);
        } catch (Exception ex) {
            throw new TigrServiceAccessException("Cannot find Animal by Name: " + name);
        }
    }

    @Override
    public List<Animal> getAll() {
        try {
            return animalDao.getAll();
        } catch (Exception ex) {
            throw new TigrServiceAccessException("Cannot return all Animals");
        }
    }

    @Override
    public List<Animal> getTopOfFoodChain() {
        List<Animal> topChain = animalDao.getAll().stream().filter(animal -> animal.getPredators().size() == 0).collect(Collectors.toList());
        topChain.sort((a1, a2) -> a2.getPreys().size() - a1.getPreys().size());
        return topChain;
    }

    private void checkNullObject(Object object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }
    }

}
