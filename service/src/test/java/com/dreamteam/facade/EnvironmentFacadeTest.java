package com.dreamteam.facade;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.dto.EnvironmentDTO;
import com.dreamteam.service.BeanMappingService;
import com.dreamteam.service.EnvironmentService;
import com.dreamteam.service.config.ServiceConfig;
import com.dreamteam.service.facade.EnvironmentFacadeImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by jan.novak
 */
@ContextConfiguration(classes = ServiceConfig.class)
@RunWith(MockitoJUnitRunner.class)
public class EnvironmentFacadeTest {

	@Mock
	private EnvironmentService envService;
	@Mock
	private BeanMappingService beanMappingService;

	@Inject
	@InjectMocks
	private EnvironmentFacadeImpl environmentFacade;

	private EnvironmentDTO forest;
	private AnimalDTO eagle;
	private AnimalDTO swallow;
	private AnimalDTO mouse;
	private AnimalDTO fly;
	private List<AnimalDTO> animals = new ArrayList<>();

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeMethod
	public void initializeEntities() {
		animals.clear();
		forest = new EnvironmentDTO();
		forest.setName("Forest");
		forest.setDescription("blablaforest");

		HashSet<EnvironmentDTO> forestSet = new HashSet<>();
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
	}

	@AfterMethod
	public void resetMocks() {
		Mockito.reset(envService);
		Mockito.reset(beanMappingService);
	}


	@Test
	public void createEnvironmentTest() {
		environmentFacade.createEnvironment(forest);
	}


}
