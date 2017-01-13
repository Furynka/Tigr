package com.dreamteam.dao;

import com.dreamteam.TigrAppContext;
import com.dreamteam.entity.Environment;
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
import java.util.List;

/**
 * @author Daniil Khudiakov
 */
@ContextConfiguration(classes = TigrAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class EnvironmentDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    public EnvironmentDao environmentDao;

    @PersistenceContext
    private EntityManager entityManager;

    private Environment env1;

	@BeforeMethod
	public void createEnvironment() {
		env1 = new Environment();
        env1.setName("env1");
        env1.setDescription("description");

        environmentDao.create(env1);
    }

	@AfterMethod
	public void deleteEnvironment() {
		environmentDao.delete(env1);
    }

    @Test()
    public void findAll(){
        List<Environment> res = environmentDao.findAll();
        Assert.assertEquals(res.size(), 1);
    }

    @Test()
    public void findById() {
        Environment res = environmentDao.findById(env1.getId());
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getName(), env1.getName());
    }

    @Test()
    public void findByName() {
        Environment res = environmentDao.findByName(env1.getName());
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getName(), env1.getName());
    }

    @Test()
    public void update() {
        env1.setName("env1_u");
        environmentDao.update(env1);

        Environment res = environmentDao.findById(env1.getId());
        Assert.assertNotNull(res);
        Assert.assertEquals(res.getName(), env1.getName());
    }

    @Test()
    public void delete() {
        Environment tmpEnv = new Environment();
        tmpEnv.setName("temp");
        tmpEnv.setDescription("temp");

        environmentDao.create(tmpEnv);
        int before = environmentDao.findAll().size();

        environmentDao.delete(tmpEnv);
        int after = environmentDao.findAll().size();

        Assert.assertEquals(before-after, 1);
    }
}
