package com.dreamteam.facade;

import com.dreamteam.dto.WorkerAuthenticateDTO;
import com.dreamteam.dto.WorkerDTO;

import java.util.Collection;

/**
 * Created by khudiakov on 21.11.2016.
*/
public interface WorkerFacade {
    void registerWorker(WorkerDTO w, String unencryptedPassword);

    boolean authenticate(WorkerAuthenticateDTO w);

    boolean isAdmin(WorkerDTO w);

    WorkerDTO findWorkerById(Long id);

    WorkerDTO findWorkerByEmail(String email);

    Collection<WorkerDTO> getAllWorkers();
}
