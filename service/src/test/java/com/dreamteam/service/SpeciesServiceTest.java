package com.dreamteam.service;

import com.dreamteam.dao.SpeciesDao;
import com.dreamteam.entity.Species;
import com.dreamteam.service.SpeciesService;
import com.dreamteam.service.config.ServiceConfig;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Created by khudiakov on 25.11.2016.
*/
@ContextConfiguration(classes=ServiceConfig.class)
public class SpeciesServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Mock
    private SpeciesDao speciesDao;

    @Inject
    @InjectMocks
    private SpeciesService speciesService;

    private Species species1;

    @BeforeClass
    public void initializeMockito() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createTestData() {
        species1 = new Species();
        species1.setId(1L);
        species1.setName("species1");
        species1.setDescription("description of species1");
        species1.setInDanger(false);
    }

    @AfterMethod
    public void mockitoReset() {
        Mockito.reset(speciesDao);
    }

    @Test
    public void testGetSpeciesById() {
        Mockito.when(speciesDao.findById(species1.getId())).thenReturn(species1);
        Species foundSpecies = speciesService.getSpeciesById(species1.getId());
        Assert.assertEquals(species1, foundSpecies);
    }

    @Test
    public void testGetAllSpecieses() {
        List<Species> speciesList = new ArrayList<>();
        speciesList.add(species1);

        Mockito.when(speciesDao.findAll()).thenReturn(speciesList);
        List<Species> listOfFoundSpecies = speciesService.getAllSpecieses();
        Assert.assertEquals(listOfFoundSpecies.size(), 1);
        Assert.assertEquals(listOfFoundSpecies.get(0), species1);
    }

    @Test
    public void testUpdateSpecies() {
        speciesService.updateSpecies(species1);
        Mockito.verify(speciesDao).update(species1);
    }


    @Test
    public void testDeleteSpecies() {
        speciesService.deleteSpecies(species1);
        Mockito.verify(speciesDao).delete(species1);
    }
}
