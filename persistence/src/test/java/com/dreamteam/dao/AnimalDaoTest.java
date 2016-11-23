package com.dreamteam.dao;

import com.dreamteam.TigrAppContext;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;
import com.dreamteam.entity.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    public SpeciesDao speDao;

    @PersistenceContext
    private EntityManager entityManager;

    private Animal eagle;
    private Animal swallow;
    private Animal fly;
    private Environment woodland;
    private Species golden;
    private Species bicolor;
    private Species common;

    @BeforeClass
    public void createObjects(){
        golden = new Species("Golden");
        bicolor = new Species("Bicolor");
        common = new Species("Common");
        speDao.create(golden);
        speDao.create(bicolor);
        speDao.create(common);

        woodland = new Environment();
        woodland.setName("Woodland");
        woodland.setDescription("40% trees, 20% mud, 30% air, 10% water");
        envDao.create(woodland);

        eagle = new Animal();
        eagle.setName("Eagle");
        eagle.setSpecies(golden);
        eagle.setDescription("Eagle lives in woodland and eats swallows.");
        eagle.addEnvironment(woodland);
        animalDao.create(eagle);

        swallow = new Animal();
        swallow.setName("Swallow");
        swallow.setSpecies(bicolor);
        swallow.setDescription("Swallow lives in woodland and eats flies.");
        swallow.addEnvironment(woodland);
        animalDao.create(swallow);

        fly = new Animal();
        fly.setName("Fly");
        fly.setSpecies(common);
        fly.setDescription("Fly lives in woodland and is eaten by swallows.");
        swallow.addEnvironment(woodland);
        animalDao.create(fly);

        eagle.addPrey(swallow);
        swallow.addPrey(fly);
    }

    @AfterClass
    public void deleteObjects(){
        animalDao.delete(swallow);
        animalDao.delete(eagle);
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
        swallow.setName("Swallow_Updated");
        animalDao.update(swallow);

        Animal found = animalDao.findById(swallow.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getName(), "Swallow_Updated");
    }

    @Test()
    public void delete() {
        Animal tmpA = new Animal();
        tmpA.setName("Animal_Tmp");
        tmpA.setDescription("description");
        tmpA.addEnvironment(woodland);
        tmpA.setSpecies(bicolor);

        animalDao.create(tmpA);

        int before = animalDao.getAll().size();

        animalDao.delete(tmpA);
        int after = animalDao.getAll().size();

        Assert.assertEquals(before - after, 1);
    }

    @Test
    public void returnsNullIfNonexistingID() {
        Assert.assertNull(animalDao.findById(11111111111l));
    }
}