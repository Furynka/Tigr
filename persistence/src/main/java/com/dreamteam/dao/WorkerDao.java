package com.dreamteam.dao;

import com.dreamteam.entity.Worker;

import java.util.List;

public interface WorkerDao {
    void create(Worker w);
    Worker findById(Long id);
    Worker findWorkerByEmail(String email);
    List<Worker> all();
}
