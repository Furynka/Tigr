package com.dreamteam.dao;

import com.dreamteam.entity.Environment;

import java.util.List;

/**
 * @author Eva Ambrusova
 */
public interface EnvironmentDao {
    public void create(Environment e);
    public Environment findById(int id);
    public Environment findByName(String name);
    public List<Environment> findAll();
    public Environment update (Environment e);
    public void delete (Environment e);
}
