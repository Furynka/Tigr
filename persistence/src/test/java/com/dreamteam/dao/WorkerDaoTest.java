package com.dreamteam.dao;

import com.dreamteam.TigrAppContext;
import com.dreamteam.entity.Worker;
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
 * Tests for Worker DAO class.
 * @author Jiri Oliva
 */
@ContextConfiguration(classes = TigrAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class WorkerDaoTest extends AbstractTestNGSpringContextTests{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private WorkerDao workerDao;
    
    private Worker worker1;
    private Worker worker2;
    private Worker worker3;

    @BeforeMethod
    public void createTestData() {
        worker1 = new Worker();
        worker2 = new Worker();
        worker3 = new Worker();
        
        worker1.setAdministrator(true);
        worker1.setEmail("worker1@dreamteam.com");
        worker1.setPasswordHash("unbreakable");
        
        worker2.setAdministrator(true);
        worker2.setEmail("worker2@dreamteam.com");
        worker2.setPasswordHash("unbreakable");
        
        worker3.setAdministrator(true);
        worker3.setEmail("worker3@dreamteam.com");
        worker3.setPasswordHash("unbreakable");
        
        workerDao.create(worker1);
        workerDao.create(worker2);
        workerDao.create(worker3);
    }

    @AfterMethod
    public void clearTestData() {
        if (workerDao.findById(worker1.getId()) != null) {
            workerDao.delete(worker1);
        }
        if (workerDao.findById(worker2.getId()) != null) {
            workerDao.delete(worker2);
        }
        if (workerDao.findById(worker3.getId()) != null) {
            workerDao.delete(worker3);
        }
    }
    
    @Test
    public void testCreate() {
        List<Worker> result = entityManager.createQuery("select w from Worker w", Worker.class).getResultList();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 3);
    }
    
    @Test
    public void testGetAll() {
        List<Worker> result = workerDao.all();
        Assert.assertEquals(result.size(), 3);
    }
    
    @Test
    public void testFindById() {
        Worker result = workerDao.findById(worker1.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getEmail(), worker1.getEmail());
        
        result = workerDao.findById(worker1.getId() + worker2.getId() + worker3.getId());
        Assert.assertNull(result);
    }
    
    @Test(expectedExceptions = RuntimeException.class)
    public void testFindByIdWithNullParameter() {
        workerDao.findById(null);
    }
    
    @Test
    public void testFindByEmail() {
        Worker result = workerDao.findWorkerByEmail(worker1.getEmail());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getEmail(), worker1.getEmail());
        
        result = workerDao.findWorkerByEmail("invalidEmail");
        Assert.assertNull(result);
    }
    
    @Test(expectedExceptions = RuntimeException.class)
    public void testFindByEmailWithNullParameter(){
        workerDao.findWorkerByEmail(null);
    }
    
    @Test
    public void testUpdate() {
        Worker result = workerDao.findById(worker1.getId());
        Assert.assertEquals(result.getEmail(), "worker1@dreamteam.com");
        worker1.setEmail("updated@dreamteam.com");
        
        workerDao.update(worker1);
        result = workerDao.findById(worker1.getId());
        Assert.assertEquals(result.getEmail(), "updated@dreamteam.com");
        Assert.assertNotEquals(result.getEmail(), "worker1@dreamteam.com");
    }
    
    @Test
    public void testDelete() {
        Worker result = workerDao.findById(worker1.getId());
        Assert.assertNotNull(result);
        workerDao.delete(worker1);
        result = workerDao.findById(worker1.getId());
        Assert.assertNull(result);        
    }
    
}
