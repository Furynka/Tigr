package com.dreamteam.service.facade;

import com.dreamteam.dto.WorkerAuthenticateDTO;
import com.dreamteam.dto.WorkerDTO;
import com.dreamteam.entity.Worker;
import com.dreamteam.facade.WorkerFacade;
import com.dreamteam.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by khudiakov on 21.11.2016.
*/
public class WorkerFacadeImpl implements WorkerFacade {
    private final WorkerService workerService;

    private final BeanMappingService beanMappingService;

    @Autowired
    public WorkerFacadeImpl(WorkerService workerService, BeanMappingService beanMappingService) {
        this.workerService = workerService;
        this.beanMappingService = beanMappingService;
    }

    public void registerWorker(WorkerDTO w, String unencryptedPassword) {
        Worker workerEntity = beanMappingService.mapTo(w, Worker.class);
        workerService.registerWorker(workerEntity, unencryptedPassword);
        w.setId(workerEntity.getId());
    }

    public boolean authenticate(WorkerAuthenticateDTO w) {
        return workerService.authenticate(
                workerService.findWorkerById(w.getId()), w.getPassword());
    }

    public boolean isAdmin(WorkerDTO w) {
        return workerService.isAdmin(beanMappingService.mapTo(w, Worker.class));
    }

    public WorkerDTO findWorkerById(Long id) {
        Worker workerEntity = workerService.findWorkerById(id);
        return (workerEntity == null) ? null : beanMappingService.mapTo(workerEntity, WorkerDTO.class);
    }

    public WorkerDTO findWorkerByEmail(String email) {
        Worker workerEntity = workerService.findWorkerByEmail(email);
        return (workerEntity == null) ? null : beanMappingService.mapTo(workerEntity, WorkerDTO.class);
    }

    public Collection<WorkerDTO> getAllWorkers() {
        return beanMappingService.mapTo(workerService.getAllWorkers(), WorkerDTO.class);
    }
}
