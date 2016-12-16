package com.dreamteam.sampledata;

import com.dreamteam.entity.Worker;
import com.dreamteam.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Loads some sample data to populate the eshop database.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private WorkerService workerService;

    @Override
    @SuppressWarnings("unused")
    public void loadData() {
        worker("test@test.ru", "password", true);
    }

    private void worker(String email, String password, boolean admin) {
        Worker worker = new Worker();
        worker.setEmail(email);
        worker.setAdministrator(admin);
        workerService.registerWorker(worker, password);
    }
}
