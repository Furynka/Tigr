package com.dreamteam.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.dreamteam.entity.Worker;

/**
 * Created by khudiakov on 21.11.2016.
*/
@Service
public interface WorkerService {
    void registerWorker(Worker w, String unencryptedPassword);

    boolean authenticate(Worker w, String password);

    boolean isAdmin(Worker w);

    List<Worker> getAllWorkers();

    Worker findWorkerById(Long workerId);

    Worker findWorkerByEmail(String email);
}