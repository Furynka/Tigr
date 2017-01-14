package com.dreamteam.service;

import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;

import java.util.List;

/**
 * @author Eva Ambrusova
 */
public interface EnvironmentService {
    void create(Environment env);
    void delete(Environment env);
    Environment findById(Long id);
    Environment findByName(String name);
    List<Environment> findAll();
    Environment update(Environment env);

    /**
     * Returns Searches for 3 most endangered animals in specified environment.
     * @param envName
     * @return List of five most endangered animals.
     */
    public List<Animal> getTopThreeEndangeredAnimals(String envName);
}
