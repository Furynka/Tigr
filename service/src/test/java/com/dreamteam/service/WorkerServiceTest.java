
package com.dreamteam.service;

import com.dreamteam.dao.WorkerDao;
import com.dreamteam.entity.Worker;
import com.dreamteam.service.config.ServiceConfig;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *  Tests for Worker service
 * 
 * @author Jiri Oliva
 */
@ContextConfiguration(classes=ServiceConfig.class)
public class WorkerServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    
    @Mock
    private WorkerDao workerDao;
    
    @Inject
    @InjectMocks
    private WorkerService workerService;
    
    private Worker worker1;
    private Worker worker2;
    
    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        
        worker1 = new Worker();
        worker1.setAdministrator(true);
        worker1.setEmail("worker1@dreamteam.com");
        worker1.setPasswordHash("password1");
        
        worker2 = new Worker();
        worker2.setAdministrator(false);
        worker2.setEmail("worker2@dreamteam.com");
        worker2.setPasswordHash("password2");
    }
    
    @Test
    public void testRegisterWorker() {
        workerService.registerWorker(worker1, "password1");
        Assert.assertEquals(workerDao.all().size(), 1);
    }
    
    @Test
    public void testAuthenticateWorker() {
        boolean result = workerService.authenticate(worker1, "password1");
        Assert.assertEquals(result, true);
        
        result = workerService.authenticate(worker1, "invalidPassword");
        Assert.assertEquals(result, false);
    }
    
    @Test
    public void testIsAdmin() {
        boolean result = workerService.isAdmin(worker1);
        Assert.assertEquals(result, true);
        
        result = workerService.isAdmin(worker2);
        Assert.assertEquals(result, false);
    }
    
    @Test
    public void testGetAllWorkers() {
        workerService.registerWorker(worker1, "password1");
        List<Worker> found = workerService.getAllWorkers();
        Assert.assertEquals(found.size(), 1);
        
        workerService.registerWorker(worker2, "password2");
        found = workerService.getAllWorkers();
        Assert.assertEquals(found.size(), 2);
    }
    
    @Test
    public void testFindWorkerById() {
        Worker found = workerService.findWorkerById(worker1.getId());
        Assert.assertNotNull(found);
        
        found = workerService.findWorkerById(worker1.getId() + worker2.getId());
        Assert.assertNull(found);        
    }
    
    @Test
    public void testFindWorkerByEmail() {
        Worker found = workerService.findWorkerByEmail(worker1.getEmail());
        Assert.assertNotNull(found);
        
        found = workerService.findWorkerByEmail("notExisting@dreamteam.com");
        Assert.assertNull(found);        
    }
    
}
