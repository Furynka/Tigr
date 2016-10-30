package com.dreamteam.dao;

import com.dreamteam.TigrAppContext;
import com.dreamteam.entity.Worker;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for Worker DAO class.
 * @author Jiri Oliva
 */
@ContextConfiguration(classes = TigrAppContext.class)
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
        
        workerDao.create(worker1);
        workerDao.create(worker2);
        workerDao.create(worker3);
    }
    
    @Test
    public void testGetAll() {
        List<Worker> result = workerDao.all();
        Assert.assertEquals(result.size(), 3);
    }
    
    @Test
    public void testFindById() {
        //Worker result = workerDao.findById(worker1.getId());
    }
    
    @Test
    public void testFindByEmail() {
        Worker result = workerDao.findWorkerByEmail(worker1.getEmail());
        Assert.assertEquals(result, worker1);
        result = workerDao.findWorkerByEmail("invalidEmail");
        Assert.assertNull(result);
    }
    
    @Test
    public void testUpdate() {
        //TODO getId()
        Worker result = workerDao.findById(1L);
        
    }
    
    @Test
    public void testDelete() {
        //TODO getId()
        
    }
    
    
}
