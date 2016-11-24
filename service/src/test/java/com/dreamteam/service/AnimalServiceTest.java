package com.dreamteam.service;

import com.dreamteam.entity.Animal;
import com.dreamteam.service.config.ServiceConfig;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eva Ambrusova
 */
@ContextConfiguration(classes=ServiceConfig.class)
public class AnimalServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private Animal animalDao;

    @InjectMocks
    private AnimalService animalService;

    private Animal eagle;
    private Animal swallow;
    private List<Animal> animals = new ArrayList<>();

    @BeforeClass
    public void initializeMockito() throws ServiceException{
        MockitoAnnotations.initMocks(this);
    }


    @BeforeMethod
    public void initializeEntities() {
        animals.clear();

       // eagle = TestUtils.createAnimal();
        eagle = new Animal();
        eagle.setId(2L);
        eagle.setName("Eagle");
        eagle.setDescription("Eagle description");
        //eagle.setSpecies();
    }
}
