package com.dreamteam.dao;

import com.dreamteam.TigrAppContext;
import com.dreamteam.entity.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Daniil Khudiakov
 */
@ContextConfiguration(classes = TigrAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class SpeciesDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    public SpeciesDao speciesDao;

    @PersistenceContext
    private EntityManager entityManager;

    private Species spec1;

    @BeforeClass
    public void createSpecies(){
        spec1 = new Species();
        spec1.setName("env1");
        spec1.setDescription("description");
        spec1.setInDanger(true);

        speciesDao.create(spec1);
    }

    @AfterClass
    public void deleteSpecies(){
        speciesDao.delete(spec1);
    }

    @Test()
    public void findAll(){
        List<Species> res = speciesDao.all();
        Assert.assertEquals(res.size(), 1);
    }

    @Test()
    public void findById() {
        Species res = speciesDao.findById(spec1.getId());
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getName(), spec1.getName());
    }

    @Test()
    public void update() {
        spec1.setName("spec1 u");
        speciesDao.update(spec1);

        Species res = speciesDao.findById(spec1.getId());
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getName(), spec1.getName());
    }

    @Test()
    public void delete() {
        Species tmpSpec = new Species();
        tmpSpec.setName("temp");
        tmpSpec.setDescription("temp");

        speciesDao.create(tmpSpec);
        int before = speciesDao.all().size();

        speciesDao.delete(tmpSpec);
        int after = speciesDao.all().size();

        Assert.assertEquals(before-after, 1);
    }
}
