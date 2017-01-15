package com.dreamteam.facade;

import com.dreamteam.dto.WorkerDTO;
import com.dreamteam.dto.WorkerIdPasswordDTO;

import java.util.Collection;

/**
 * Created by khudiakov on 21.11.2016.
*/
public interface WorkerFacade {
    void registerWorker(WorkerDTO w, String unencryptedPassword);

    void changePassword(WorkerIdPasswordDTO w);

    void changeRole(WorkerDTO w);

    boolean authenticate(WorkerIdPasswordDTO w);

    boolean isAdmin(WorkerDTO w);

    WorkerDTO findWorkerById(Long id);

    WorkerDTO findWorkerByEmail(String email);

    Collection<WorkerDTO> getAllWorkers();
}
