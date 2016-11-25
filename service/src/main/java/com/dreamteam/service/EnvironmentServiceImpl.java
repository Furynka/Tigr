package com.dreamteam.service;

import com.dreamteam.dao.EnvironmentDao;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

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


    /**
     * Searches for 3 most endangered animals in specified environment.
     * @param envName is environment searched for endangered species
     * @return List of thre most endangered animals.
     */

    @Override
    public List<Animal> getTopThreeEndangeredAnimals(String envName){

        Environment e = (envDao.findByName(envName));
        if(e == null)
            throw new NullPointerException();

        List<Animal> allAnimals =  e.getAnimals();

        if (allAnimals == null)
            throw new NullPointerException();

        Collections.sort(allAnimals, new AnimalComparator());

        List<Animal> resultList = new ArrayList<Animal>();

        int loopCount = 0;
        for(Iterator<Animal> i = allAnimals.iterator(); i.hasNext(); ) {

            if(loopCount == 3)
                break;

            resultList.add(i.next());
            loopCount++;
        }

        return resultList;
    }

    private class AnimalComparator implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
            return Integer.compare(o1.getCount(), o2.getCount());
        }
    }

    }
