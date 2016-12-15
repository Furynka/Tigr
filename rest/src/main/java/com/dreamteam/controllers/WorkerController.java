package com.dreamteam.controllers;

import com.dreamteam.ApiUris;
import com.dreamteam.dto.WorkerDTO;
import com.dreamteam.facade.WorkerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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

    final static Logger logger = LoggerFactory.getLogger(WorkerController.class);

    @Inject
    private WorkerFacade workerFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<WorkerDTO> getWorkers(){
        logger.debug("rest getWorkers()");
        return workerFacade.getAllWorkers();
    }
}
