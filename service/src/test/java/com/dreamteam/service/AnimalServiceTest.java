package com.dreamteam.service;

import com.dreamteam.dao.AnimalDao;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;
import com.dreamteam.service.config.ServiceConfig;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * @author Eva Ambrusova
 */
@ContextConfiguration(classes=ServiceConfig.class)
public class AnimalServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private AnimalDao animalDao;

    @Inject
    @InjectMocks
    private AnimalService animalService;

    private Animal eagle;
    private Environment forest;
    private List<Animal> animalList = new ArrayList<>();


    @BeforeClass
    public void initMockito() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createTestData() {
        forest = new Environment();
        forest.setName("Forest");
        forest.setDescription("blablaforest");
        List<Environment> forestSet = new ArrayList<>();
        forestSet.add(forest);

        eagle = new Animal();
        eagle.setId(2L);
        eagle.setName("Eagle");
        eagle.setDescription("Eagle description");
        eagle.setCount(10);

        animalList.add(eagle);
    }

    @AfterMethod
    public void mockitoReset() {
        Mockito.reset(animalDao);
    }

    @Test
    public void createTest() {
        animalService.create(eagle);
        verify(animalDao).create(eagle);
    }

    @Test
    public void testGetAnimalById() {
        Mockito.when(animalDao.findById(eagle.getId())).thenReturn(eagle);
        Animal foundAnimal = animalService.findById(eagle.getId());
        Assert.assertEquals(eagle, foundAnimal);
    }

    @Test
    public void testGetAnimalByName() {
        Mockito.when(animalDao.findByName(eagle.getName())).thenReturn(eagle);
        Animal foundAnimal = animalService.findByName(eagle.getName());
        Assert.assertEquals(eagle, foundAnimal);
    }

    @Test
    public void testGetAllAnimals() {

        Mockito.when(animalDao.getAll()).thenReturn(animalList);
        Assert.assertEquals(animalService.getAll().size(), animalList.size());
        Assert.assertEquals(animalService.getAll().get(0), eagle);
    }

    @Test
    public void testUpdateAnimal() {
        animalService.update(eagle);
        Mockito.verify(animalDao).update(eagle);
    }

    @Test
    public void testDeleteAnimal() {
        animalService.delete(eagle);
        Mockito.verify(animalDao).delete(eagle);
    }

}















