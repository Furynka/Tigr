
package com.dreamteam.service.facade;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.dto.EnvironmentDTO;
import com.dreamteam.dto.SpeciesDTO;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;
import com.dreamteam.entity.Species;
import com.dreamteam.facade.AnimalFacade;
import com.dreamteam.service.AnimalService;
import com.dreamteam.service.BeanMappingService;
import com.dreamteam.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation class for Animal facade layer. 
 * 
 * @author Jiri Oliva
 */
@Service
@Transactional
public class AnimalFacadeImpl implements AnimalFacade{
    
    @Inject
    private AnimalService animalService;
	@Inject
	private SpeciesService speciesService;

	@Autowired
    private BeanMappingService beanMappingService;

    @Override
	public void createAnimal(AnimalDTO animalDTO) {
		//Animal newAnimal = beanMappingService.mapTo(animal, Animal.class);
		Animal animal = new Animal();
		animal.setName(animalDTO.getName());
		animal.setDescription(animalDTO.getDescription());
		animal.setCount(animalDTO.getCount());

		//if - because you dont set species in sampleData
		if (animalDTO.getSpeciesId() != null)
			animal.setSpecies(speciesService.getSpeciesById(animalDTO.getSpeciesId()));
		animalService.create(animal);
	}

    @Override
    public AnimalDTO findAnimalById(Long id) {
        Animal found = animalService.findById(id);
        return found == null ? null : beanMappingService.mapTo(found, AnimalDTO.class);
    }

    @Override
    public AnimalDTO findAnimalByName(String name) {
        Animal found = animalService.findByName(name);
        return found == null ? null : beanMappingService.mapTo(found, AnimalDTO.class);
    }

    @Override
    public List<AnimalDTO> getAllAnimals() {
        List<Animal> foundAnimals = animalService.getAll();
        List<AnimalDTO> result = new ArrayList<>();
        for (Animal animal : foundAnimals)
        {
            result.add(beanMappingService.mapTo(animal, AnimalDTO.class));
        }
        return result;
    }

    @Override
    public void deleteAnimal(long id) {
        Animal found = animalService.findById(id);
        animalService.delete(found);
    }

    @Override
    public void changeAnimalName(long id, String newName) {
        Animal found = animalService.findById(id);
        found.setName(newName);
        animalService.update(found);
    }

    @Override
    public void changeAnimalDescription(long id, String description) {
        Animal found = animalService.findById(id);
        found.setDescription(description);
        animalService.update(found);
    }

    @Override
    public void changeAnimalSpecies(long id, SpeciesDTO species) {
        Animal found = animalService.findById(id);
        found.setSpecies(beanMappingService.mapTo(species, Species.class));
        animalService.update(found);
    }
    
    @Override
    public void changeAnimalCount(long id, int count) {
        Animal found = animalService.findById(id);
        found.setCount(count);
        animalService.update(found);
    }

    @Override
    public void addAnimalPredator(long id, AnimalDTO predator) {
        Animal found = animalService.findById(id);
        found.addPredator(beanMappingService.mapTo(predator, Animal.class));
        animalService.update(found);
    }

    @Override
    public void addAnimalPrey(long id, AnimalDTO prey) {
        Animal found = animalService.findById(id);
        found.addPrey(beanMappingService.mapTo(prey, Animal.class));
        animalService.update(found);
    }

    @Override
    public void addAnimalEnvironment(long id, EnvironmentDTO environment) {
        Animal found = animalService.findById(id);
        found.addEnvironment(beanMappingService.mapTo(environment, Environment.class));
        animalService.update(found);
    }

    @Override
    public void removeAnimalPredator(long id, AnimalDTO predator) {
        Animal found = animalService.findById(id);
        found.removePredator(beanMappingService.mapTo(predator, Animal.class));
        animalService.update(found);
    }

    @Override
    public void removeAnimalPrey(long id, AnimalDTO prey) {
        Animal found = animalService.findById(id);
        found.removePrey(beanMappingService.mapTo(prey, Animal.class));
        animalService.update(found);
    }

    @Override
    public void removeAnimalEnvironment(long id, EnvironmentDTO environment) {
        Animal found = animalService.findById(id);
        found.removeEnvironment(beanMappingService.mapTo(environment, Environment.class));
        animalService.update(found);
    }

    @Override
    public List<AnimalDTO> getTopOfFoodChain() {
        return beanMappingService.mapTo(animalService.getTopOfFoodChain(), AnimalDTO.class);
    }
}
