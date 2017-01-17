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

        species("Tiger", "Largest cat species, most recognisable for their pattern of dark vertical stripes on reddish-orange fur with a lighter underside.", false);
        species("Brown bear", " Large bear with the widest distribution of any living ursid.", false);
        species("Red fox", "Largest of the true foxes, has the greatest geographic range of all members of the Carnivora family, being present across the entire Northern Hemisphere from the Arctic Circle to North Africa, North America and Eurasia.", false);
        species("Elk", "One of the largest species within the deer family, Cervidae, in the world, and one of the largest land mammals in North America and Eastern Asia.", false);
        species("Hare", "Leporids belonging to the genus Lepus.", false);
        species("Old World flying squirrel", "The genus Pteromys is distributed across temperate Eurasia, Korean Peninsula and Japan.", false);
        species("Roe deer", "Eurasian species of deer.", false);

        EnvironmentDTO forest = environment("Forest", "Large area dominated by trees.");
        EnvironmentDTO steppe = environment("Steppe", "Ecoregion, in the montane grasslands and shrublands and temperate grasslands, savannas, and shrublands biomes.");
        EnvironmentDTO mountain = environment("Mountain", "Large landform that stretches above the surrounding land in a limited area, usually in the form of a peak. ");

        animal("Siberian tiger", "Also called Amur tiger is a tiger subspecies inhabiting mainly the Sikhote Alin mountain region with a small population in southwest Primorye Province in the Russian Far East.", 1);
        animalFacade.changeAnimalSpecies(animalFacade.getAllAnimals().get(0).getId(), speciesFacade.getAllSpecieses().get(0));
        animalFacade.addAnimalEnvironment(animalFacade.getAllAnimals().get(0).getId(), environmentFacade.findEnvironmentByName(forest.getName()));
        animalFacade.addAnimalEnvironment(animalFacade.getAllAnimals().get(0).getId(), environmentFacade.findEnvironmentByName(steppe.getName()));
        animal("East Siberian brown bear", "Subspecies of brown bear which ranges from eastern Siberia, beginning at the Yenisei river, as far as Trans-Baikaliya, the Stanovoy Range, the Lena River, Kolyma.", 1);
        animalFacade.changeAnimalSpecies(animalFacade.getAllAnimals().get(1).getId(), speciesFacade.getAllSpecieses().get(1));
        animalFacade.addAnimalPredator(animalFacade.getAllAnimals().get(1).getId(), animalFacade.getAllAnimals().get(0));
        animalFacade.addAnimalEnvironment(animalFacade.getAllAnimals().get(1).getId(), environmentFacade.findEnvironmentByName(forest.getName()));
        animalFacade.addAnimalEnvironment(animalFacade.getAllAnimals().get(1).getId(), environmentFacade.findEnvironmentByName(steppe.getName()));
        animal("Russian Domesticated Red Fox", "Domesticated form of the red fox (Vulpes vulpes).", 1);
        animalFacade.changeAnimalSpecies(animalFacade.getAllAnimals().get(2).getId(), speciesFacade.getAllSpecieses().get(2));
        animalFacade.addAnimalPredator(animalFacade.getAllAnimals().get(2).getId(), animalFacade.getAllAnimals().get(0));
        animalFacade.addAnimalEnvironment(animalFacade.getAllAnimals().get(2).getId(), environmentFacade.findEnvironmentByName(steppe.getName()));
        animal("Manchurian wapiti", "Subspecies of Cervus canadensis (named \"elk\" or \"wapiti\" in North America), native to eastern Asia.", 1);
        animalFacade.changeAnimalSpecies(animalFacade.getAllAnimals().get(3).getId(), speciesFacade.getAllSpecieses().get(3));
        animalFacade.addAnimalPredator(animalFacade.getAllAnimals().get(3).getId(), animalFacade.getAllAnimals().get(0));
        animalFacade.addAnimalPredator(animalFacade.getAllAnimals().get(3).getId(), animalFacade.getAllAnimals().get(1));
        animalFacade.addAnimalEnvironment(animalFacade.getAllAnimals().get(3).getId(), environmentFacade.findEnvironmentByName(forest.getName()));
        animalFacade.addAnimalEnvironment(animalFacade.getAllAnimals().get(3).getId(), environmentFacade.findEnvironmentByName(steppe.getName()));
        animal("Mountain hare", "Hare that is largely adapted to polar and mountainous habitats.", 1);
        animalFacade.changeAnimalSpecies(animalFacade.getAllAnimals().get(4).getId(), speciesFacade.getAllSpecieses().get(4));
        animalFacade.addAnimalPredator(animalFacade.getAllAnimals().get(4).getId(), animalFacade.getAllAnimals().get(0));
        animalFacade.addAnimalPredator(animalFacade.getAllAnimals().get(4).getId(), animalFacade.getAllAnimals().get(2));
        animalFacade.addAnimalEnvironment(animalFacade.getAllAnimals().get(4).getId(), environmentFacade.findEnvironmentByName(steppe.getName()));
        animalFacade.addAnimalEnvironment(animalFacade.getAllAnimals().get(4).getId(), environmentFacade.findEnvironmentByName(mountain.getName()));
        animal("Siberian flying squirrel", "Old World flying squirrel with a range from the Baltic Sea in the west to the Pacific coast in the east.", 1);
        animalFacade.changeAnimalSpecies(animalFacade.getAllAnimals().get(5).getId(), speciesFacade.getAllSpecieses().get(5));
        animalFacade.addAnimalPredator(animalFacade.getAllAnimals().get(5).getId(), animalFacade.getAllAnimals().get(2));
        animalFacade.addAnimalEnvironment(animalFacade.getAllAnimals().get(5).getId(), environmentFacade.findEnvironmentByName(forest.getName()));
        animal("Siberian roe deer", "Species of roe deer found in northeastern Asia.", 1);
        animalFacade.changeAnimalSpecies(animalFacade.getAllAnimals().get(6).getId(), speciesFacade.getAllSpecieses().get(6));
        animalFacade.addAnimalPredator(animalFacade.getAllAnimals().get(6).getId(), animalFacade.getAllAnimals().get(0));
        animalFacade.addAnimalPredator(animalFacade.getAllAnimals().get(6).getId(), animalFacade.getAllAnimals().get(1));
        animalFacade.addAnimalEnvironment(animalFacade.getAllAnimals().get(6).getId(), environmentFacade.findEnvironmentByName(forest.getName()));
        animalFacade.addAnimalEnvironment(animalFacade.getAllAnimals().get(6).getId(), environmentFacade.findEnvironmentByName(steppe.getName()));
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
