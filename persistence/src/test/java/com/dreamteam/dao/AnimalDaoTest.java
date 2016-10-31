package com.dreamteam.dao;

import com.dreamteam.TigrAppContext;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * AnimalDaoTest class implements unit tests for AnimalDao class.
 *
 * @author Eva Ambrusova
 */
@ContextConfiguration(classes = TigrAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class AnimalDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    public AnimalDao animalDao;

    @Autowired
    public EnvironmentDao envDao;

    @PersistenceContext
    private EntityManager entityManager;

    private Animal eagle;
    private Animal swallow;
    private Animal fly;
    private Environment woodland;

    @BeforeClass
    public void createObjects(){
        woodland = new Environment();
        woodland.setName("Woodland");
        woodland.setDescription("40% trees, 20% mud, 30% air, 10% water");
        envDao.create(woodland);

        eagle = new Animal();
        eagle.setName("Eagle");
        eagle.setSpecies("Golden");
        eagle.setDescription("Eagle lives in woodland and eats swallows.");
        eagle.addEnvironment(woodland);
        animalDao.create(eagle);

        swallow = new Animal();
        swallow.setName("Swallow");
        swallow.setSpecies("Bicolor");
        swallow.setDescription("Swallow lives in woodland and eats flies.");
        swallow.addEnvironment(woodland);
        animalDao.create(swallow);

        fly = new Animal();
        fly.setName("Fly");
        fly.setSpecies("Common");
        fly.setDescription("Fly lives in woodland and is eaten by swallows.");
        animalDao.create(fly);

        eagle.addPrey(swallow);
        swallow.addPrey(fly);
        swallow.addPredator(eagle);
        fly.addPredator(swallow);

        woodland.addAnimal(eagle);
        woodland.addAnimal(swallow);
        woodland.addAnimal(fly);
    }

    @AfterClass
    public void deleteObjects(){
        animalDao.delete(eagle);
        animalDao.delete(swallow);
        animalDao.delete(fly);
        envDao.delete(woodland);
    }

    @Test()
    public void findAll(){
        List<Animal> animals = animalDao.getAll();
        Assert.assertEquals(animals.size(), 3);
    }

    @Test()
    public void findById() {
        Animal found = animalDao.findById(eagle.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getName(), eagle.getName());
    }

    @Test()
    public void findByName() {
        Animal found = animalDao.findByName(eagle.getName());
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getId(), eagle.getId());
    }

    @Test()
    public void update() {
        eagle.setName("Eagle_Updated");
        animalDao.update(eagle);

        Animal found = animalDao.findById(eagle.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getName(), eagle.getName());
    }

}