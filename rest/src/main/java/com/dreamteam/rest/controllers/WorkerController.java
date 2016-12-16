package com.dreamteam.rest.controllers;

import com.dreamteam.dto.WorkerDTO;
import com.dreamteam.facade.WorkerFacade;
import com.dreamteam.rest.ApiUris;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;

/**
 * REST controller for Worker
 *
 * @author Eva Ambrusova
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_WORKER)
public class WorkerController {
    @Inject
    private WorkerFacade workerFacade;

    /**
     * Get collection of Workers
     * Should be available on http://localhost:8080/pa165/rest/worker
     *
     * @return Collection<WorkerDTO>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<WorkerDTO> getWorkers(){
        return workerFacade.getAllWorkers();
    }

    /**
     *
     *
     * @param id id of Worker
     * @return WorkerDTO
     * @throws IllegalArgumentException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final WorkerDTO getWorkerById(@PathVariable("id") long id) throws Exception {
        WorkerDTO workerDTO = workerFacade.findWorkerById(id);
        if (workerDTO != null) {
            return workerDTO;
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     *
     * @param email email of Worker
     * @return WorkerDTO
     * @throws IllegalArgumentException
     */
    @RequestMapping(value = "/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final WorkerDTO getWorkerByEmail(@PathVariable("email") String email) throws Exception {
        WorkerDTO workerDTO = workerFacade.findWorkerByEmail(email);
        if (workerDTO != null) {
            return workerDTO;
        } else {
            throw new IllegalArgumentException();
        }

    }
}
