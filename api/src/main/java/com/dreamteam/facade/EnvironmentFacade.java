package com.dreamteam.facade;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.dto.EnvironmentDTO;

import java.util.Collection;
import java.util.Set;

/**
 * @author Eva Ambrusova
 */
public interface EnvironmentFacade {

    public void createEnvironment(EnvironmentDTO e);

    public void changeName(String newName, EnvironmentDTO e);

    public void changeDescription(String description, EnvironmentDTO e);

    EnvironmentDTO findEnvironmentById(int id);

    EnvironmentDTO findEnvironmentByName(String name);

    public void addAnimal(int environmentId, Long animalId);

    public Set<AnimalDTO> getAllAnimals(EnvironmentDTO e);

    Collection<EnvironmentDTO> getAllEnvironments();

    public void deleteEnvironment(int envId);


    public void deleteAnimal(AnimalDTO animal, EnvironmentDTO e);

}
