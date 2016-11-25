//package com.dreamteam.service;
//
//import com.dreamteam.dao.AnimalDao;
//import com.dreamteam.dao.EnvironmentDao;
//import com.dreamteam.entity.Animal;
//import com.dreamteam.entity.Environment;
//import com.dreamteam.service.config.ServiceConfig;
//import org.hibernate.service.spi.ServiceException;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Eva Ambrusova
// */
//@ContextConfiguration(classes=ServiceConfig.class)
//public class AnimalServiceTest extends AbstractTransactionalTestNGSpringContextTests {
//
//    @Mock
//    private AnimalDao animalDao;
//
//    @Mock
//    private EnvironmentDao envDao;
//
//    @Autowired
//    @InjectMocks
//    private AnimalService animalService;
//
//    @BeforeClass
//    public void initializeMockito() throws ServiceException {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    private Animal eagle;
//    private Animal swallow;
//    private List<Animal> animals = new ArrayList<>();
//    private Environment forest;
//
//    @BeforeMethod
//    public void initializeEntities() {
//        animals.clear();
//
//        forest = new Environment();
//        forest.setName("Forest");
//        forest.setDescription("blablaforest");
//
//        eagle = new Animal();
//        eagle.setId(2L);
//        eagle.setName("Eagle");
//        eagle.setDescription("Eagle description");
//        eagle.setCount(2);
//        //eagle.setSpecies();
//        eagle.addEnvironment(forest);
//
//        swallow = new Animal();
//        swallow.setName("Eagle");
//        swallow.setCount(6);
//        swallow.setDescription("blaswallow");
//        swallow.setId(3L);
//        swallow.addEnvironment(forest);
//
//        forest.addAnimal(eagle);
//        forest.addAnimal(swallow);
//    }
//
//   /*
//*/
//
//
//    @Test
//    public void testtest(){
//
//        animalService.getTopFiveEndangeredAnimals(forest);
//        assert true;
//    }
//}
