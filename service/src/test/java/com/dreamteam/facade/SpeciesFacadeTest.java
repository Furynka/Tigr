package com.dreamteam.facade;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.dto.SpeciesDTO;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Species;
import com.dreamteam.service.AnimalService;
import com.dreamteam.service.BeanMappingService;
import com.dreamteam.service.SpeciesService ;
import com.dreamteam.service.config.ServiceConfig;
import com.dreamteam.service.facade.SpeciesFacadeImpl;
import org.dozer.inject.Inject;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khudiakov on 25.11.2016.
*/
@ContextConfiguration(classes=ServiceConfig.class)
public class SpeciesFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private SpeciesService speciesService;
    @Mock
    private AnimalService animalService;

    @Mock
    private BeanMappingService mapper;

    @Inject
    @InjectMocks
    private SpeciesFacadeImpl speciesFacade;

    private SpeciesDTO speciesDTO;

    private Species species;
    private Animal animal;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createEntities() {
        speciesDTO = new SpeciesDTO();
        speciesDTO.setId(1L);
        speciesDTO.setName("speciesDTO");
        speciesDTO.setDescription("description of speciesDTO");
        speciesDTO.setAnimals(new ArrayList<>());

        species = new Species();
        species.setId(speciesDTO.getId());
        species.setName(speciesDTO.getName());
        species.setDescription(speciesDTO.getDescription());

        animal = new Animal();
        animal.setId(1L);
        animal.setName("animal1");
        animal.setDescription("description of animal1");
    }

    @AfterMethod
    public void mockitoReset() {
        Mockito.reset(speciesService);
        Mockito.reset(animalService);
    }

    @Test
    public void createSpeciesTest() {
        speciesFacade.createSpecies(speciesDTO);
        Mockito.verify(speciesService).createSpecies(species);
    }

    @Test
    public void deleteSpecies() {
        Mockito.when(speciesService.getSpeciesById(speciesDTO.getId())).thenReturn(species);
        speciesFacade.deleteSpecies(speciesDTO.getId());
        Mockito.verify(speciesService).deleteSpecies(species);
    }
    @Test
    public void changeSpeciesName() {
        final String newName = "newName";
        speciesDTO.setName(newName);

        Mockito.when(speciesService.getSpeciesById(speciesDTO.getId())).thenReturn(species);
        speciesFacade.changeSpeciesName(speciesDTO.getId(), newName);
        Mockito.verify(speciesService).updateSpecies(species);
    }
    @Test
    public void changeSpeciesDescription() {
        final String newDescription = "newDescription";
        speciesDTO.setDescription(newDescription);

        Mockito.when(speciesService.getSpeciesById(speciesDTO.getId())).thenReturn(species);
        speciesFacade.changeSpeciesDescription(speciesDTO.getId(), newDescription);
        Mockito.verify(speciesService).updateSpecies(species);
    }

    @Test
    public void setSpeciesInDanger() {
        speciesDTO.setInDanger(true);

        Mockito.when(speciesService.getSpeciesById(speciesDTO.getId())).thenReturn(species);
        speciesFacade.setSpeciesInDanger(speciesDTO.getId());
        Mockito.verify(speciesService).updateSpecies(species);
    }
    @Test
    public void setSpeciesNotInDanger() {
        speciesDTO.setInDanger(false);

        Mockito.when(speciesService.getSpeciesById(speciesDTO.getId())).thenReturn(species);
        speciesFacade.setSpeciesNotInDanger(speciesDTO.getId());
        Mockito.verify(speciesService).updateSpecies(species);
    }

    @Test
    public void addAnimalIntoSpecies(){
        species.addAnimal(animal);

        Mockito.when(speciesService.getSpeciesById(speciesDTO.getId())).thenReturn(species);
        Mockito.when(animalService.findById(animal.getId())).thenReturn(animal);
        speciesFacade.addAnimalIntoSpecies(animal.getId(), speciesDTO.getId());
        Mockito.verify(speciesService).updateSpecies(species);
    }
    @Test(dependsOnMethods = {"addAnimalIntoSpecies"})
    public void removeAnimalFromSpecies(){
        species.removeAnimal(animal);

        Mockito.when(speciesService.getSpeciesById(speciesDTO.getId())).thenReturn(species);
        Mockito.when(animalService.findById(animal.getId())).thenReturn(animal);
        speciesFacade.removeAnimalFromSpecies(animal.getId(), speciesDTO.getId());
        Mockito.verify(speciesService).updateSpecies(species);
    }
    @Test
    public void getSpeciesById(){
        Mockito.when(speciesService.getSpeciesById(species.getId())).thenReturn(species);
        Mockito.when(mapper.mapTo(species.getAnimals(), AnimalDTO.class)).thenReturn(speciesDTO.getAnimals());
        SpeciesDTO resultSpeciesDTO = speciesFacade.getSpeciesById(this.speciesDTO.getId());
        Assert.assertEquals(resultSpeciesDTO, speciesDTO);
    }
    @Test
    public void getAllSpecieses(){
        List<Species> speciesList = new ArrayList<>();
        speciesList.add(species);

        Mockito.when(mapper.mapTo(species.getAnimals(), AnimalDTO.class)).thenReturn(speciesDTO.getAnimals());
        Mockito.when(speciesService.getAllSpecieses()).thenReturn(speciesList);
        List<SpeciesDTO> speciesDTOs = speciesFacade.getAllSpecieses();
        Assert.assertEquals(speciesDTOs.size(), 1);
        Assert.assertEquals(speciesDTOs.get(0), speciesDTO);
    }
}
