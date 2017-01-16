package com.dreamteam.sampledata;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.dto.EnvironmentDTO;
import com.dreamteam.dto.SpeciesDTO;
import com.dreamteam.dto.WorkerDTO;
import com.dreamteam.facade.AnimalFacade;
import com.dreamteam.facade.EnvironmentFacade;
import com.dreamteam.facade.SpeciesFacade;
import com.dreamteam.facade.WorkerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by khudiakov on 15.12.2016.
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private WorkerFacade workerFacade;
	@Autowired
	private SpeciesFacade speciesFacade;
    @Autowired
    private AnimalFacade animalFacade;
    @Autowired
    private EnvironmentFacade environmentFacade;

    @Override
    @SuppressWarnings("unused")
    public void loadData() {
        worker("admin@test.com", "password", true);
		worker("worker@test.com", "password", false);


        AnimalDTO animalDTO = animal("Animal1", "Animal1Description", 1);
        long animalId = animalFacade.findAnimalByName("Animal1").getId();

        EnvironmentDTO environmentDTO = environment("Environment1", "Environment1Description");
        environmentFacade.addAnimal((long) 1, animalId);
		SpeciesDTO speciesDTO = species("Species1", "Species1Descrition", true);
        speciesFacade.addAnimalIntoSpecies(animalId,
                                           speciesFacade.getAllSpecieses().get(0).getId());
		species("Species2", "Species2Descrition", false);
		species("Species3", "Species3Descrition", true);

        environment("Environment2", "Environment1Description 2");
	}

	private SpeciesDTO species(String name, String description, boolean inDanger) {
		SpeciesDTO speciesDTO = new SpeciesDTO();
		speciesDTO.setName(name);
		speciesDTO.setDescription(description);
		speciesDTO.setInDanger(inDanger);
		speciesFacade.createSpecies(speciesDTO);
        return speciesDTO;
	}

    private void worker(String email, String password, boolean admin) {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setEmail(email);
        workerDTO.setAdministrator(admin);
        workerFacade.registerWorker(workerDTO, password);
    }

    private AnimalDTO animal(String name, String description, int count) {
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setName(name);
        animalDTO.setDescription(description);
        animalDTO.setCount(count);
        animalFacade.createAnimal(animalDTO);
        return animalDTO;
    }

    private EnvironmentDTO environment(String name, String description) {
        EnvironmentDTO environmentDTO = new EnvironmentDTO();
        environmentDTO.setName(name);
        environmentDTO.setDescription(description);
        environmentFacade.createEnvironment(environmentDTO);
        return environmentDTO;
    }
}
