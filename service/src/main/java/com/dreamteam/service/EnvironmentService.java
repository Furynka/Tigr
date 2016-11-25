package com.dreamteam.service;

import com.dreamteam.entity.Environment;

import java.util.List;

/**
 * @author Eva Ambrusova
 */
public interface EnvironmentService {
    void create(Environment env);
    void delete(Environment env);
    Environment findById(int id);
    Environment findByName(String name);
    List<Environment> findAll();
    Environment update(Environment env);

}
