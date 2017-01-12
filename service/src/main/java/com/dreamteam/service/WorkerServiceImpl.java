package com.dreamteam.service;

import com.dreamteam.dao.WorkerDao;
import com.dreamteam.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.List;

/**
 * Created by khudiakov on 21.11.2016.
*/
@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerDao workerDao;

    public void registerWorker(Worker w, String unencryptedPassword) {
        w.setPasswordHash(BCrypt.hashpw(unencryptedPassword, BCrypt.gensalt()));
        workerDao.create(w);
    }

    public void changePassword(Worker w, String unencryptedPassword) {
        w.setPasswordHash(BCrypt.hashpw(unencryptedPassword, BCrypt.gensalt()));
        workerDao.update(w);
    }

    public boolean authenticate(Worker w, String password) {
        return BCrypt.checkpw(password, w.getPasswordHash());
    }

    public boolean isAdmin(Worker w) {
        return w.getAdministrator();
    }

    public Worker findWorkerById(Long workerId) {
        return workerDao.findById(workerId);
    }

    public Worker findWorkerByEmail(String email) {
        return workerDao.findWorkerByEmail(email);
    }

    public List<Worker> getAllWorkers() {
        return workerDao.all();
    }
}
