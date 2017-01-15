package com.dreamteam;

import com.dreamteam.dao.EnvironmentDao;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;
import com.dreamteam.service.EnvironmentService;
import com.dreamteam.service.config.ServiceConfig;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Eva Ambrusova
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class EnvironmentServiceTest extends AbstractTransactionalTestNGSpringContextTests {

	private static Animal eagle;
	private static Animal swallow;
	private static Animal mouse;
	private static Animal fly;
	private static Environment forest;

	@Mock
	private EnvironmentDao daoMock;

	@Autowired
	@InjectMocks
	private EnvironmentService service;

	public static Environment getForest() {
		Environment forest = new Environment();
		forest.setName("Forest");
		forest.setDescription("blablaforest");

		eagle = new Animal();
		eagle.setId(2L);
		eagle.setName("Eagle");
		eagle.setDescription("Eagle description");
		eagle.setCount(10);
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

		return forest;
	}

	@BeforeClass
	public void initializeMockito() throws ServiceException {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeMethod
	public void resetMocks() {
		Mockito.reset(daoMock);
		forest = getForest();
	}

	@Test
	public void createTest() {
		service.create(forest);
		verify(daoMock).create(forest);
	}

	@Test
	public void updateTest() {
		service.update(forest);
		verify(daoMock).update(forest);
	}

	@Test
	public void deleteTest() {
		service.delete(forest);
		verify(daoMock).delete(forest);
	}

	@Test
	public void findByIdTest() {
		when(daoMock.findById(1)).thenReturn(forest);
		assertEquals(forest, service.findById(1));
	}

	@Test
	public void findByNameTest() {
		when(daoMock.findByName("forest")).thenReturn(forest);
		assertEquals(forest, service.findByName("forest"));
	}

	@Test
	public void findAllTest() {
		when(daoMock.findAll()).thenReturn(Arrays.asList(forest));
		assertTrue(service.findAll().size() == 1);
		assertEquals(service.findAll().get(0), forest);
	}

	@Test
	public void testgetTopThreeEndangeredAnimals() {
		List<Animal> compareList = new ArrayList<>();
		compareList.add(fly);
		compareList.add(mouse);
		compareList.add(swallow);

		when(daoMock.findByName("Forest")).thenReturn(forest);
		List<Animal> result = service.getTopThreeEndangeredAnimals("Forest");

		assertEquals(result.size(), 3);
		assertTrue(result.containsAll(compareList));
	}
}
