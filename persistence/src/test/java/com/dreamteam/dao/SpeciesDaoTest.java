package com.dreamteam.dao;

import com.dreamteam.TigrAppContext;
import com.dreamteam.entity.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

/**
 * @author Daniil Khudiakov
 */
@ContextConfiguration(classes = TigrAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SpeciesDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SpeciesDao speciesDao;

    @PersistenceContext
    private EntityManager entityManager;

    private static Species getDummySpecies1() {
        Species species = new Species();
        species.setName("env1");
        species.setDescription("description");
        species.setInDanger(true);
        return species;
    }

    @BeforeMethod
    public void createSpecies(){
        entityManager.persist(getDummySpecies1());
    }

    @AfterMethod
    public void deleteSpecies(){
        for (Species species : getAllSpeciesFromEntityManager()) {
            entityManager.remove(species);
        }
    }

    @Test
    public void findAll(){
        Collection<Species> allFromEntityManager = getAllSpeciesFromEntityManager();
        List<Species> allFromDAO = speciesDao.findAll();

        Assert.assertTrue(allFromDAO.size() == allFromEntityManager.size());
        Assert.assertTrue(allFromDAO.containsAll(allFromEntityManager));
    }

    @Test
    public void findById() {
        for (Species species : getAllSpeciesFromEntityManager()) {
            Assert.assertEquals(species, speciesDao.findById(species.getId()));
        }
    }

    @Test
    public void create() {
        Species species1 = getDummySpecies1();
        species1.setName("second");

        int sizeBeforeSave = getAllSpeciesFromEntityManager().size();
        speciesDao.create(species1);
        Assert.assertTrue(getAllSpeciesFromEntityManager().size() == sizeBeforeSave + 1);
    }

    @Test
    public void delete() {
        Collection<Species> allSpeciesFromEntityManager = getAllSpeciesFromEntityManager();
        int remaining = allSpeciesFromEntityManager.size();

        for (Species species : allSpeciesFromEntityManager) {
            speciesDao.delete(species);
            Assert.assertTrue(--remaining == getAllSpeciesFromEntityManager().size());
        }
    }

    @Test
    public void update() {
        Collection<Species> allSpeciesFromEntityManager = getAllSpeciesFromEntityManager();

        for (Species species : allSpeciesFromEntityManager) {
            Species species1 = new Species();
            species1.setId(species.getId());
            species1.setName(species.getName() + "newNameSuffix");
            species1.setDescription(species.getDescription() + "newDescriptionSuffix");
            species1.setInDanger(!species.isInDanger());

            speciesDao.update(species1);

            Assert.assertEquals(species1, entityManager.find(Species.class, species1.getId()));
        }
    }

    private Collection<Species> getAllSpeciesFromEntityManager() {
        return entityManager.createQuery("SELECT s FROM Species s", Species.class).getResultList();
    }
}
