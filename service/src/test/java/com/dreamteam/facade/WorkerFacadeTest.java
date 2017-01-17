/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.facade;

import com.dreamteam.dto.WorkerDTO;
import com.dreamteam.entity.Worker;
import com.dreamteam.service.BeanMappingService;
import com.dreamteam.service.WorkerService;
import com.dreamteam.service.config.ServiceConfig;
import com.dreamteam.service.facade.WorkerFacadeImpl;
import javax.inject.Inject;
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
public class WorkerFacadeTest {
    @Mock
    private WorkerService workerService;
    
    @InjectMocks
    private WorkerFacadeImpl workerFacade;
    
    private Worker testWorker;
    private WorkerDTO testWorkerDTO;
    
    
    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void prepareTestData() {
        testWorkerDTO = new WorkerDTO();
        testWorkerDTO.setId(1L);
        testWorkerDTO.setEmail("test@test.com");
        testWorkerDTO.setAdministrator(true);
        testWorkerDTO.setPasswordHash("password");
        
        testWorker = new Worker();
        testWorker.setId(1L);
        testWorker.setEmail("test@test.com");
        testWorker.setAdministrator(true);
        testWorker.setPasswordHash("password");
    }
    
    @AfterMethod
    public void mockitoReset() {
        Mockito.reset(workerService);
    }
    
    @Test
    public void getWorkerById(){
        Mockito.when(workerService.findWorkerById(testWorker.getId())).thenReturn(testWorker);
        //WorkerDTO found = workerFacade.findWorkerById(testWorkerDTO.getId());
        //Assert.assertEquals(found, testWorkerDTO);
    }
    
    @Test
    public void getWorkerByEmail(){
        Mockito.when(workerService.findWorkerByEmail(testWorker.getEmail())).thenReturn(testWorker);
        //WorkerDTO found = workerFacade.findWorkerByEmail(testWorkerDTO.getEmail());
        //Assert.assertEquals(found, testWorkerDTO);
    }
    
    
}
