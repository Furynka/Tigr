package com.dreamteam.service;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.dto.EnvironmentDTO;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;
import com.dreamteam.service.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eva Ambrusova
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BeanMappingService beanMappingService;

    private List<Animal> animals = new ArrayList<>();
    private List<Environment> environments = new ArrayList<Environment>();

    @BeforeMethod
    public void createEntities(){
        Environment forest = new Environment();

        forest.setName("Forest");
        forest.setDescription("blablaforest");

        Animal eagle = new Animal();
        eagle.setName("Eagle");
        eagle.setCount(2);
        eagle.setDescription("blablaeagle");
        eagle.setId(2L);
        eagle.addEnvironment(forest);

        Animal swallow = new Animal();
        swallow.setName("Eagle");
        swallow.setCount(6);
        swallow.setDescription("blaswallow");
        swallow.setId(3L);
        swallow.addEnvironment(forest);

        animals.add(eagle);
        animals.add(swallow);

        forest.addAnimal(eagle);
        forest.addAnimal(swallow);

        environments.add(forest);
    }

    @Test
    public void testMapEnvironments() {
       List<EnvironmentDTO> envDTOs = beanMappingService.mapTo(environments, EnvironmentDTO.class);
       Assert.assertEquals(envDTOs.get(0).getName(), "Forest");
    }

    @Test
    public void testMapAnimals() {
        List<AnimalDTO> animalDTOs = beanMappingService.mapTo(animals, AnimalDTO.class);
        Assert.assertEquals(animalDTOs.get(0).getName(), "Eagle");
    }
}
