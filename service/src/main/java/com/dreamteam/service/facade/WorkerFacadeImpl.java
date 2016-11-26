package com.dreamteam.service.facade;

import com.dreamteam.dto.WorkerIdPasswordDTO;
import com.dreamteam.dto.WorkerDTO;
import com.dreamteam.entity.Worker;
import com.dreamteam.facade.WorkerFacade;
import com.dreamteam.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import com.dreamteam.service.BeanMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by khudiakov on 21.11.2016.
*/
@Service
@Transactional
public class WorkerFacadeImpl implements WorkerFacade {
    @Autowired
    private WorkerService workerService;

    @Autowired
    private BeanMappingService beanMappingService;

    public void registerWorker(WorkerDTO w, String unencryptedPassword) {
        Worker workerEntity = beanMappingService.mapTo(w, Worker.class);
        workerService.registerWorker(workerEntity, unencryptedPassword);
        w.setId(workerEntity.getId());
    }

    public void changePassword(WorkerIdPasswordDTO w) {
        workerService.changePassword(workerService.findWorkerById(w.getId()), w.getPassword());
    }

    public boolean authenticate(WorkerIdPasswordDTO w) {
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
