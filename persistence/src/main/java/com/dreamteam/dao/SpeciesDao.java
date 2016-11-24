package com.dreamteam.dao;

import com.dreamteam.entity.Species;

import java.util.List;

/**
 * Created by jan.novak
 */
public interface SpeciesDao {
    void create(Species s);

    void update(Species s);

    void delete(Species s);

    Species findById(Long id);

    List<Species> all();

    List<Species> findAllInDanger();
}
