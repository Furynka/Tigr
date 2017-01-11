package com.dreamteam.service;

import com.dreamteam.dao.SpeciesDao;
import com.dreamteam.entity.Species;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by jan.novak
 */
@Service
public class SpeciesServiceImpl implements SpeciesService {

    @Inject
    private SpeciesDao speciesDao;

    @Override
    public void createSpecies(Species species) {
        speciesDao.create(species);
    }

    @Override
    public void deleteSpecies(Species species) {
        speciesDao.delete(species);
    }

    @Override
    public void updateSpecies(Species species) {
        speciesDao.update(species);
    }

    @Override
    public Species getSpeciesById(long id) {
        return speciesDao.findById(id);
    }

    @Override
    public List<Species> getAllSpecieses() {
		return speciesDao.findAll();
	}
}
