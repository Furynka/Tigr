package com.dreamteam.service_layer;

import com.dreamteam.dao.EnvironmentDao;
import com.dreamteam.entity.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * This class provides implementation of business logic.
 *
 * @author Eva Ambrusova
 */

@Service
public class EnvironmentServiceImpl implements EnvironmentService{
    @Inject
    private EnvironmentDao envDao;

    @Override
    public void create(Environment env){
        envDao.create(env);
    }

    @Override
    public void delete(Environment env){
        envDao.delete(env);
    }

    @Override
    public Environment findById(int id){
        return envDao.findById(id);
    }

    @Override
    public Environment findByName(String name){
        return envDao.findByName(name);
    }

    @Override
    public List<Environment> findAll(){
        return envDao.findAll();
    }

    @Override
    public Environment update(Environment env){
        return envDao.update(env);
    }

}
