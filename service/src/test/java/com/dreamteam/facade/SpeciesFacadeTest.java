package com.dreamteam.facade;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
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

    @Autowired
    private BeanMappingService mapper;

    @Inject
    @InjectMocks
    private SpeciesFacadeImpl speciesFacade;

    private Species species1;
    private Animal animal1;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);

        species1 = new Species();
        species1.setId(1L);
        species1.setName("species1");
        species1.setDescription("description of species1");
        species1.setAnimals(new ArrayList<>());

        animal1 = new Animal();
        animal1.setId(1L);
        animal1.setName("animal1");
        animal1.setDescription("description of animal1");
    }

    @BeforeMethod
    public void mockitoReset() {
        Mockito.reset(speciesService);
        Mockito.reset(animalService);
    }

    /*@Test
    public void createSpeciesTest() {
        SpeciesDTO speciesDTO = mapper.mapTo(species1, SpeciesDTO.class);
        speciesFacade.createSpecies(speciesDTO);
        Mockito.verify(speciesService).createSpecies(species1);
    }

    @Test
    public void deleteSpecies() {
        Mockito.when(speciesService.getSpeciesById(species1.getId())).thenReturn(species1);
        speciesFacade.deleteSpecies(species1.getId());
        Mockito.verify(speciesService).deleteSpecies(species1);
    }
    @Test
    public void changeSpeciesName() {
        final String newName = "newName";
        species1.setName(newName);

        Mockito.when(speciesService.getSpeciesById(species1.getId())).thenReturn(species1);
        speciesFacade.changeSpeciesName(species1.getId(), newName);
        Mockito.verify(speciesService).updateSpecies(species1);
    }
    @Test
    public void changeSpeciesDescription() {
        final String newDescription = "newDescription";
        species1.setDescription(newDescription);

        Mockito.when(speciesService.getSpeciesById(species1.getId())).thenReturn(species1);
        speciesFacade.changeSpeciesDescription(species1.getId(), newDescription);
        Mockito.verify(speciesService).updateSpecies(species1);
    }

    @Test
    public void setSpeciesInDanger() {
        species1.setInDanger(true);

        Mockito.when(speciesService.getSpeciesById(species1.getId())).thenReturn(species1);
        speciesFacade.setSpeciesInDanger(species1.getId());
        Mockito.verify(speciesService).updateSpecies(species1);
    }
    @Test
    public void setSpeciesNotInDanger() {
        species1.setInDanger(false);

        Mockito.when(speciesService.getSpeciesById(species1.getId())).thenReturn(species1);
        speciesFacade.setSpeciesNotInDanger(species1.getId());
        Mockito.verify(speciesService).updateSpecies(species1);
    }

    @Test
    public void addAnimalIntoSpecies(){
        species1.addAnimal(animal1);

        Mockito.when(speciesService.getSpeciesById(species1.getId())).thenReturn(species1);
        Mockito.when(animalService.findById(animal1.getId())).thenReturn(animal1);
        speciesFacade.addAnimalIntoSpecies(animal1.getId(), species1.getId());
        Mockito.verify(speciesService).updateSpecies(species1);
    }
    @Test(dependsOnMethods = {"addAnimalIntoSpecies"})
    public void removeAnimalFromSpecies(){
        species1.removeAnimal(animal1);

        Mockito.when(speciesService.getSpeciesById(species1.getId())).thenReturn(species1);
        Mockito.when(animalService.findById(animal1.getId())).thenReturn(animal1);
        speciesFacade.removeAnimalFromSpecies(animal1.getId(), species1.getId());
        Mockito.verify(speciesService).updateSpecies(species1);
    }
    @Test
    public void getSpeciesById(){
        Mockito.when(speciesService.getSpeciesById(species1.getId())).thenReturn(species1);
        SpeciesDTO speciesDTO = speciesFacade.getSpeciesById(species1.getId());
        SpeciesDTO waiting =  mapper.mapTo(species1, SpeciesDTO.class);
        Assert.assertEquals(speciesDTO, waiting);
    }
    @Test
    public void getAllSpecieses(){
        List<Species> speciesList = new ArrayList<>();
        speciesList.add(species1);

        Mockito.when(speciesService.getAllSpecieses()).thenReturn(speciesList);
        List<SpeciesDTO> speciesDTOs = speciesFacade.getAllSpecieses();
        Assert.assertEquals(speciesDTOs.size(), 1);
        SpeciesDTO waiting = mapper.mapTo(species1, SpeciesDTO.class);
        Assert.assertEquals(speciesDTOs.get(0), waiting);
    }*/
}
