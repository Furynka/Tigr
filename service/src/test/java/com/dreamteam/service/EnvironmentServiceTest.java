package com.dreamteam.service;

import com.dreamteam.dao.AnimalDao;
import com.dreamteam.dao.EnvironmentDao;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;
import com.dreamteam.service.config.ServiceConfig;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eva Ambrusova
 */
@ContextConfiguration(classes=ServiceConfig.class)
public class EnvironmentServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private AnimalDao animalDao;

    @Autowired
    @InjectMocks
    private EnvironmentDao envDao;

    @Autowired
    @InjectMocks
    private EnvironmentService envService ;

    @BeforeClass
    public void initializeMockito() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Animal eagle;
    private Animal swallow;
    private Animal mouse;
    private Animal fly;
    private List<Animal> animals = new ArrayList<>();
    private Environment forest;

    @BeforeMethod
    public void initializeEntities() {
        animals.clear();

        forest = new Environment();
        forest.setName("Forest");
        forest.setDescription("blablaforest");


        eagle = new Animal();
        eagle.setId(2L);
        eagle.setName("Eagle");
        eagle.setDescription("Eagle description");
        eagle.setCount(10);
        //eagle.setSpecies();
        eagle.addEnvironment(forest);

        swallow = new Animal();
        swallow.setName("Eagle");
        swallow.setCount(3);
        swallow.setDescription("blaswallow");
        swallow.setId(3L);
        swallow.addEnvironment(forest);

        mouse = new Animal();
        mouse.setName("Mouse");
        mouse.setCount(2);
        mouse.setDescription("blaswallow");
        mouse.setId(3L);
        mouse.addEnvironment(forest);

        fly = new Animal();
        fly.setName("Fly");
        fly.setCount(1);
        fly.setDescription("blaswallow");
        fly.setId(3L);
        fly.addEnvironment(forest);

        forest.addAnimal(eagle);
        forest.addAnimal(swallow);
        forest.addAnimal(mouse);
        forest.addAnimal(fly);

        envDao.create(forest);

    }

    @Test
    public void testtest(){
        List<Animal> compareList = new ArrayList<>();
        compareList.add(fly);
        compareList.add(mouse);
        compareList.add(swallow);

        List<Animal> result = envService.getTopThreeEndangeredAnimals("Forest");
        Assert.assertEquals(result.size(),3);
        Assert.assertEquals(result,compareList);

    }
}
