package com.dreamteam.facade;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.service.AnimalService;
import com.dreamteam.service.EnvironmentService;
import com.dreamteam.service.config.ServiceConfig;
import com.dreamteam.service.facade.AnimalFacadeImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

/**
 * Tests for AnimalFacade class.
 *
 * @author Eva Ambrusova
 */
@ContextConfiguration(classes=ServiceConfig.class)
public class AnimalFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @InjectMocks
    private AnimalFacade animalFacade = new AnimalFacadeImpl();

    @Mock
    private AnimalService animalService;

    @Mock
    private EnvironmentService envService;

    private AnimalDTO eagle;
    private AnimalDTO swallow;
    private AnimalDTO animalToCreate;

    @BeforeClass
    public void prepareMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void prepareEntities() {

        swallow = new AnimalDTO();
        swallow.setId(4L);
        swallow.setName("Swallow");
        swallow.setDescription("swallowDTO description");

        eagle = new AnimalDTO();
        eagle.setId(3L);
        eagle.setName("Eagle");
        eagle.setDescription("eagleDTO description");

        animalToCreate = new AnimalDTO();
        animalToCreate.setId(2L);
        animalToCreate.setName("New animal");
        animalToCreate.setDescription("New animal description");

        animalFacade.createAnimal(swallow);
        assertTrue((animalFacade.getAllAnimals()).contains(swallow));

        eagle = animalFacade.findAnimalById(eagle.getId());
        assertTrue((animalFacade.getAllAnimals()).contains(eagle));
    }

    @AfterMethod
    public void deleteEntities(){
        if (animalFacade.getAllAnimals().contains(eagle)){
            animalFacade.deleteAnimal(eagle.getId());
            assertFalse((animalFacade.getAllAnimals()).contains(eagle));
        }
    }

    @Test
    public void testFindById() throws Exception{
        assertNotNull(eagle);
        assertEquals(animalFacade.findAnimalById(eagle.getId()), eagle);
    }

    @Test
    public void testFindByName() throws Exception{
        assertNotNull(eagle);
        assertEquals(animalFacade.findAnimalByName(eagle.getName()), eagle);
    }

    @Test
    public void testGetAllAnimals() throws Exception{
        assertTrue(animalFacade.getAllAnimals().contains(swallow));
        assertTrue(animalFacade.getAllAnimals().contains(eagle));
    }

    @Test
    public void testCreate() throws Exception{
        animalFacade.createAnimal(animalToCreate);
        assertTrue(animalFacade.getAllAnimals().contains(animalToCreate));
        assertEquals(animalFacade.findAnimalById(animalToCreate.getId()), animalToCreate);
    }

    @Test
    public void testUpdateName() throws Exception{
        animalFacade.changeAnimalName(eagle.getId(), "NewEagle");
        assertEquals(eagle.getName(), "NewEagle");
    }

    @Test
    public void testUpdateDescription() throws Exception{
        animalFacade.changeAnimalDescription(eagle.getId(), "NewEagleDescription");
        assertEquals(eagle.getDescription(), "NewEagleDescription");
    }

    @Test
    public void testDelete() throws Exception{
        assertNotNull(animalFacade.findAnimalById(eagle.getId()));
        animalFacade.deleteAnimal(eagle.getId());
        assertFalse(animalFacade.getAllAnimals().contains(eagle));
    }

}
