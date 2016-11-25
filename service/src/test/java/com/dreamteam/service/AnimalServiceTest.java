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
public class AnimalServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private EnvironmentDao envDao;

    @Mock
    private AnimalDao animalDao;

    @Autowired
    @InjectMocks
    private AnimalService animalService;

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
        eagle.addEnvironment(forest);
        animalService.create(eagle);

        swallow = new Animal();
        swallow.setName("Swallow");
        swallow.setCount(3);
        swallow.setDescription("blaswallow");
        swallow.setId(3L);
        animalService.create(swallow);
        swallow.addEnvironment(forest);

        mouse = new Animal();
        mouse.setName("Mouse");
        mouse.setCount(2);
        mouse.setDescription("blaswallow");
        mouse.setId(3L);
        animalService.create(mouse);
        mouse.addEnvironment(forest);

        fly = new Animal();
        fly.setName("Fly");
        fly.setCount(1);
        fly.setDescription("blaswallow");
        fly.setId(3L);
        animalService.create(fly);
        fly.addEnvironment(forest);

        forest.addAnimal(eagle);
        forest.addAnimal(swallow);
        forest.addAnimal(mouse);
        forest.addAnimal(fly);

        envDao.create(forest);

    }


    @Test
    public void testgetAllAnimals () throws Exception {
        Assert.assertEquals(animalService.getAll().size(), 4);
    }

    @Test
    public void testcreate() {

        Animal snake = new Animal();
        snake.setId(2L);
        snake.setName("Snake");
        snake.setDescription("Snake description");
        snake.setCount(3);
        snake.addEnvironment(forest);

        animalService.create(snake);

        Animal result = animalService.findByName("Snake");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getName(),"Snake");
    }

    @Test
    public void testdelete() {

        Animal result = animalService.findById(mouse.getId());
        Assert.assertNotNull(result);
        animalService.delete(mouse);
        result = animalService.findById(mouse.getId());
        Assert.assertNull(result);

    }

    @Test
    public void testupdate() {

        Animal result = animalService.findById(fly.getId());
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result.getName(),"Not_fly");

        result.setName("Not_fly");
        animalService.update(result);

        Assert.assertEquals(result.getName(),"Not_fly");

    }

    @Test
    public void testfindById() {
        Animal result = animalService.findById(fly.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(),fly.getId());
    }

    @Test
    public void testfindByName() {
        Animal result = animalService.findByName(swallow.getName());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getName(),swallow.getName());
    }


}
