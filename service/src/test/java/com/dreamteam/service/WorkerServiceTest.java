/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.service;

import com.dreamteam.dao.WorkerDao;
import com.dreamteam.entity.Worker;
import com.dreamteam.service.config.ServiceConfig;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
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

    private Worker testWorker;
    
    @BeforeClass
    public void initializeMockito() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void createTestData() {
        testWorker = new Worker();
        testWorker.setEmail("test@test.com");
        testWorker.setAdministrator(true);
        testWorker.setPasswordHash("password");
    }
    
    @AfterMethod
    public void mockitoReset() {
        Mockito.reset(workerDao);
    }
    
    @Test
    public void testFindWorkerById() {
        Mockito.when(workerDao.findById(testWorker.getId())).thenReturn(testWorker);
        Worker found = workerService.findWorkerById(testWorker.getId());
        Assert.assertEquals(testWorker, found);
    }
    
    @Test
    public void testFindWorkerByEmail() {
        Mockito.when(workerDao.findWorkerByEmail(testWorker.getEmail())).thenReturn(testWorker);
        Worker found = workerService.findWorkerByEmail(testWorker.getEmail());
        Assert.assertEquals(testWorker, found);
    }
    
    @Test
    public void testChangeOfPassword() {
        workerService.changePassword(testWorker, "passwordd");
        Mockito.verify(workerDao).update(testWorker);
    }
    
    @Test
    public void testChangeOfRole() {
        workerService.changeRole(testWorker, Boolean.FALSE);
        Mockito.verify(workerDao).update(testWorker);
    }
    
}
