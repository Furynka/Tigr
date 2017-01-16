package com.dreamteam.facade;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.dto.EnvironmentDTO;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;
import com.dreamteam.service.AnimalService;
import com.dreamteam.service.BeanMappingService;
import com.dreamteam.service.EnvironmentService;
import com.dreamteam.service.EnvironmentServiceTest;
import com.dreamteam.service.config.ServiceConfig;
import com.dreamteam.service.facade.EnvironmentFacadeImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan.novak
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class EnvironmentFacadeTest {

	@Mock
	private EnvironmentService serviceMock;
	@Mock
	private AnimalService animalService;
	@Mock
	private BeanMappingService mappingMock;

	@InjectMocks
	private EnvironmentFacadeImpl environmentFacade;

	private EnvironmentDTO forest;
	private AnimalDTO eagle;
	private AnimalDTO swallow;
	private AnimalDTO mouse;
	private AnimalDTO fly;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeMethod
	public void initializeEntities() {
		Mockito.reset(serviceMock);
		Mockito.reset(mappingMock);


		forest = new EnvironmentDTO();
		forest.setName("Forest");
		forest.setDescription("blablaforest");

		List<EnvironmentDTO> forestSet = new ArrayList<>();
		forestSet.add(forest);

		eagle = new AnimalDTO();
		eagle.setId(2L);
		eagle.setName("Eagle");
		eagle.setDescription("Eagle description");
		eagle.setCount(10);
		eagle.setEnvironments(forestSet);

		swallow = new AnimalDTO();
		swallow.setName("Eagle");
		swallow.setCount(3);
		swallow.setDescription("blaswallow");
		swallow.setId(3L);
		swallow.setEnvironments(forestSet);

		mouse = new AnimalDTO();
		mouse.setName("Mouse");
		mouse.setCount(2);
		mouse.setDescription("blaswallow");
		mouse.setId(3L);
		mouse.setEnvironments(forestSet);

		fly = new AnimalDTO();
		fly.setName("Fly");
		fly.setCount(1);
		fly.setDescription("blaswallow");
		fly.setId(3L);
		fly.setEnvironments(forestSet);

		forest.addAnimal(eagle);
		forest.addAnimal(swallow);
		forest.addAnimal(mouse);
		forest.addAnimal(fly);

		Mockito.when(mappingMock.mapTo(forest, Environment.class))
				.thenReturn(EnvironmentServiceTest.getForest());
		Mockito.when(mappingMock.mapTo(eagle, Animal.class))
				.thenReturn(EnvironmentServiceTest.getForest().getAnimals().get(0));
		Mockito.when(mappingMock.mapTo(swallow, Animal.class))
				.thenReturn(EnvironmentServiceTest.getForest().getAnimals().get(1));
		Mockito.when(mappingMock.mapTo(mouse, Animal.class))
				.thenReturn(EnvironmentServiceTest.getForest().getAnimals().get(2));
		Mockito.when(mappingMock.mapTo(fly, Animal.class))
				.thenReturn(EnvironmentServiceTest.getForest().getAnimals().get(3));
	}


	@Test
	public void createEnvironmentTest() {
		environmentFacade.createEnvironment(forest);
		Mockito.verify(serviceMock).create(EnvironmentServiceTest.getForest());
	}

	@Test
	public void updateNameTest() {
		environmentFacade.changeName("newName", forest);

		Environment forestEntity = EnvironmentServiceTest.getForest();
		forestEntity.setName("newName");
		Mockito.verify(serviceMock).update(forestEntity);
	}

	@Test
	public void updateDescriptionTest() {
		environmentFacade.changeDescription("newD", forest);

		Environment forestEntity = EnvironmentServiceTest.getForest();
		forestEntity.setDescription("newD");
		Mockito.verify(serviceMock).update(forestEntity);
	}

	@Test
	public void deleteEnvironmentTest() {
		Mockito.when(serviceMock.findById(1L)).thenReturn(EnvironmentServiceTest.getForest());
		environmentFacade.deleteEnvironment(1L);
		Mockito.verify(serviceMock).findById(1L);
		Mockito.verify(serviceMock).delete(EnvironmentServiceTest.getForest());
	}

}
