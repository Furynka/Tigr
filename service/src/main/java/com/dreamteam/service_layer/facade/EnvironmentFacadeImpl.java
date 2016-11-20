package com.dreamteam.service_layer.facade;


import com.dreamteam.facade.EnvironmentFacade;
import com.dreamteam.service_layer.BeanMappingService;
import com.dreamteam.service_layer.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eva Ambrusova
 */
@Service
@Transactional
public class EnvironmentFacadeImpl implements EnvironmentFacade{

    @Autowired
    private BeanMappingService beanMappingService;

    @Autowired
    private EnvironmentService envService;
}
