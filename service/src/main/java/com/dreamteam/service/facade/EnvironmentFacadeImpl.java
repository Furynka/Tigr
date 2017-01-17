package com.dreamteam.service.facade;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.dto.EnvironmentDTO;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;
import com.dreamteam.facade.EnvironmentFacade;
import com.dreamteam.service.AnimalService;
import com.dreamteam.service.BeanMappingService;
import com.dreamteam.service.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Eva Ambrusova
 */
@Service
@Transactional
public class EnvironmentFacadeImpl implements EnvironmentFacade {

    @Inject
    private EnvironmentService envService;
    @Inject
    private AnimalService animalService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public void createEnvironment(EnvironmentDTO environmentDTO) {
        //Environment mappedEnv = beanMappingService.mapTo(e, Environment.class);
        Environment mappedEnv = new Environment(environmentDTO.getId());


		mappedEnv.setName(environmentDTO.getName());
		mappedEnv.setDescription(environmentDTO.getDescription());

		for (AnimalDTO animalDTO : environmentDTO.getAnimals()) {
			Animal mappedAnimalEntity = beanMappingService.mapTo(animalDTO, Animal.class);
			if (mappedAnimalEntity == null)
				throw new IllegalStateException("Mapped entity is null! DTO from FE: " + environmentDTO + ", animal: " + animalDTO);

			mappedEnv.addAnimal(mappedAnimalEntity);
		}

        //save Environment
        envService.create(mappedEnv);
    }

    @Override
    public void changeName(String newName, EnvironmentDTO e) {
        Environment mappedEnv = beanMappingService.mapTo(e, Environment.class);
        mappedEnv.setName(newName);
        envService.update(mappedEnv);
    }

    @Override
    public void changeDescription(String description, EnvironmentDTO e) {
        Environment mappedEnv = beanMappingService.mapTo(e, Environment.class);
        mappedEnv.setDescription(description);
        envService.update(mappedEnv);
    }

    @Override
    public EnvironmentDTO findEnvironmentById(Long id) {
        Environment envEntity = envService.findById(id);
        return (envEntity == null) ? null : beanMappingService.mapTo(envEntity, EnvironmentDTO.class);
    }

    @Override
    public EnvironmentDTO findEnvironmentByName(String name) {
        Environment envEntity = envService.findByName(name);
        return (envEntity == null) ? null : beanMappingService.mapTo(envEntity, EnvironmentDTO.class);
    }

    @Override
    public void addAnimal(Long environmentId, Long animalId) {
        Environment mappedEnv = envService.findById(environmentId);
        Animal mappedAnimal = animalService.findById(animalId);
        mappedAnimal.addEnvironment(mappedEnv);
        animalService.update(mappedAnimal);
    }

    @Override
    public Collection<AnimalDTO> getAllAnimals(EnvironmentDTO e) {
        Environment mappedEnv = beanMappingService.mapTo(e, Environment.class);

        return e.getAnimals();
    }

    @Override
    public Collection<EnvironmentDTO> getAllEnvironments() {
        //return beanMappingService.mapTo(envService.findAll(), EnvironmentDTO.class);
        List<Environment>  envs = envService.findAll();
        List<EnvironmentDTO> dtos = new ArrayList<>();

        for (Environment env : envs) {
            EnvironmentDTO dto = new EnvironmentDTO();
            dto.setDescription(env.getDescription());
            dto.setName(env.getName());
            dto.setId(env.getId());
            dto.setAnimals(beanMappingService.mapTo(env.getAnimals(), AnimalDTO.class));
            dtos.add(dto);
        }

        //return beanMappingService.mapTo(envService.findAll(), EnvironmentDTO.class);
        return dtos;
    }

    @Override
    public void deleteAnimal(AnimalDTO animal, EnvironmentDTO e) {
        Environment mappedEnv = beanMappingService.mapTo(e, Environment.class);
        Animal mappedAnimal = beanMappingService.mapTo(animal, Animal.class);

        mappedEnv.removeAnimal(mappedAnimal);
        envService.update(mappedEnv);
    }

    public void deleteEnvironment(Long envId){

        Environment found = envService.findById(envId);

        //Constraint violation
        List<Animal> animals = found.getAnimals();

        for (Animal animal : animals) {
            animal.removeEnvironment(found);
            animalService.update(animal);
        }
        envService.delete(found);
    }

    @Override
    public List<AnimalDTO> getTopThreeEndangeredAnimals(String envName) {
        return beanMappingService.mapTo(envService.getTopThreeEndangeredAnimals(envName), AnimalDTO.class);
    }
}
