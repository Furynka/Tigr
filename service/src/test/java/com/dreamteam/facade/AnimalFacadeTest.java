package com.dreamteam.facade;

import com.dreamteam.service.EnvironmentServiceTest;
import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.dto.EnvironmentDTO;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;
import com.dreamteam.service.AnimalService;
import com.dreamteam.service.BeanMappingService;
import com.dreamteam.service.EnvironmentService;
import com.dreamteam.service.config.ServiceConfig;
import com.dreamteam.service.facade.AnimalFacadeImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eva Ambrusova
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class AnimalFacadeTest {
    @Mock
    private EnvironmentService envService;
    @Mock
    private AnimalService animalService;
    @Mock
    private BeanMappingService mappingMock;

    @InjectMocks
    private AnimalFacadeImpl animalFacade;

    private EnvironmentDTO forestDTO;
    private AnimalDTO eagleDTO;

    private Animal eagle;
    private Environment forest;


    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initEntities() {
        forestDTO = new EnvironmentDTO();
        forestDTO.setName("Forest");
        forestDTO.setDescription("blablaforest");
        List<EnvironmentDTO> forestSet = new ArrayList<>();
        forestSet.add(forestDTO);

        eagleDTO = new AnimalDTO();
        eagleDTO.setId(2L);
        eagleDTO.setName("Eagle");
        eagleDTO.setDescription("Eagle description");
        eagleDTO.setCount(10);
        eagleDTO.setEnvironments(forestSet);

        forestDTO.addAnimal(eagleDTO);

        eagle = new Animal();
        eagle.setId(eagleDTO.getId());
        eagle.setName(eagleDTO.getName());
        eagle.setDescription(eagleDTO.getDescription());

        forest = new Environment();
        eagle.addEnvironment(forest);

        Mockito.when(mappingMock.mapTo(forestDTO, Environment.class))
                .thenReturn(EnvironmentServiceTest.getForest());
        Mockito.when(mappingMock.mapTo(eagleDTO, Animal.class))
                .thenReturn(EnvironmentServiceTest.getForest().getAnimals().get(0));
    }

    @AfterMethod
    public void mockitoReset() {
        Mockito.reset(envService);
        Mockito.reset(animalService);
    }

    @Test
    public void changeAnimalNameTest() {
        final String newName = "newName";
        eagleDTO.setName(newName);

        Animal updatedEagle = new Animal();
        updatedEagle.setId(eagle.getId());
        updatedEagle.setName(newName);
        updatedEagle.setDescription(eagle.getDescription());
        updatedEagle.addEnvironment(forest);

        Mockito.when(animalService.findById(eagleDTO.getId())).thenReturn(eagle);
        animalFacade.changeAnimalName(eagleDTO.getId(), newName);
        Mockito.verify(animalService).update(updatedEagle);
    }

    @Test
    public void changeAnimalDescriptionTest() {
        final String newDescription = "newDescription";
        eagleDTO.setDescription(newDescription);

        Animal updatedEagle = new Animal();
        updatedEagle.setId(eagle.getId());
        updatedEagle.setName(eagle.getName());
        updatedEagle.setDescription(newDescription);
        updatedEagle.addEnvironment(forest);

        Mockito.when(animalService.findById(eagleDTO.getId())).thenReturn(eagle);
        animalFacade.changeAnimalDescription(eagleDTO.getId(), newDescription);
        Mockito.verify(animalService).update(updatedEagle);
    }

    @Test
    public void deleteAnimalTest() {
        Mockito.when(animalService.findById(eagleDTO.getId())).thenReturn(eagle);
        animalFacade.deleteAnimal(eagleDTO.getId());
        Mockito.verify(animalService).delete(eagle);
    }


}





