package com.dreamteam.rest.controllers;

import com.dreamteam.dto.WorkerDTO;
import com.dreamteam.facade.WorkerFacade;
import com.dreamteam.rest.ApiUris;
import com.dreamteam.rest.exceptions.ResourceAlreadyExistingException;
import com.dreamteam.rest.exceptions.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

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
    
    private static final Logger LOG = LoggerFactory.getLogger(WorkerController.class);

    /**
     * Get collection of Workers
     * Should be available on http://localhost:8080/pa165/rest/worker
     *
     * @return Collection<WorkerDTO>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<WorkerDTO> getWorkers(){
        LOG.debug("REST call - get all workers");
        return workerFacade.getAllWorkers();
    }

    /**
     *
     * @param id id of Worker
     * @return WorkerDTO
     * @throws IllegalArgumentException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final WorkerDTO getWorkerById(@PathVariable("id") long id) throws Exception {
        LOG.debug("REST call - get worker by id = " + id);
        WorkerDTO workerDTO = workerFacade.findWorkerById(id);
        if (workerDTO != null) {
            return workerDTO;
        } else {
            throw new ResourceNotFoundException();
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
        LOG.debug("REST call - get worker by email = " + email);
        WorkerDTO workerDTO = workerFacade.findWorkerByEmail(email);
        if (workerDTO != null) {
            return workerDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }
    
    /**
     * 
     * @param worker new worker
     * @throws Exception 
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void createWorker(@RequestBody WorkerDTO worker) throws Exception {
        LOG.debug("REST call - create new worker");
        try {
            workerFacade.registerWorker(worker, worker.getEmail());
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }
    
    /**
     * 
     * @param worker
     * @throws Exception 
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void updateWorker(@RequestBody WorkerDTO worker) throws Exception {
        LOG.debug("REST call - update existing worker");
        try {
            //workerFacade.update(worker);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
    
    /**
     * 
     * @param id
     * @throws Exception 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteWorker(@PathVariable("id") long id) throws Exception {
        LOG.debug("REST call - delete existing worker");
        try {
            //workerFacade.delete(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
