package com.dreamteam.service;

import com.dreamteam.entity.Worker;

import java.util.List;

/**
 * Created by khudiakov on 21.11.2016.
*/
public interface WorkerService {
    void registerWorker(Worker w, String unencryptedPassword);

    void changePassword(Worker w, String password);

    boolean authenticate(Worker w, String password);

    boolean isAdmin(Worker w);

    List<Worker> getAllWorkers();

    Worker findWorkerById(Long workerId);

    Worker findWorkerByEmail(String email);
}
