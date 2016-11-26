package com.dreamteam.service;

import com.dreamteam.dao.SpeciesDao;
import com.dreamteam.entity.Species;
import com.dreamteam.service.config.ServiceConfig;
import org.hibernate.service.spi.ServiceException;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by khudiakov on 25.11.2016.
*/
@ContextConfiguration(classes=ServiceConfig.class)
public class SpeciesServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private SpeciesDao speciesDao;

    @Autowired
    private SpeciesService speciesService;

    private Species species1;

    @BeforeClass
    public void initializeMockito() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createTestData() {
        species1 = new Species();
        species1.setName("species1");
        species1.setDescription("description of species1");
        species1.setInDanger(false);
        speciesDao.create(species1);
    }

    @Test
    public void testGetSpeciesById() {
        Species foundSpecies = speciesService.getSpeciesById(species1.getId());
        Assert.assertEquals(species1, foundSpecies);
    }

    @Test
    public void testGetAllSpecieses() {
        List<Species> listOfFoundSpecies = speciesService.getAllSpecieses();
        Assert.assertEquals(listOfFoundSpecies.size(), 1);
        Assert.assertEquals(listOfFoundSpecies.get(0), species1);
    }

    @Test
    public void testUpdateSpecies() {
        species1.setName("updated");
        speciesService.updateSpecies(species1);

        Species dbSpecies = speciesService.getSpeciesById(species1.getId());
        Assert.assertEquals(species1, dbSpecies);
    }


    @Test
    public void testDeleteSpecies() {
        Assert.assertEquals(speciesService.getAllSpecieses().size(), 1);

        speciesService.deleteSpecies(species1);

        Assert.assertEquals(speciesService.getAllSpecieses().size(), 0);
    }
}
