package com.dreamteam.service;

import com.dreamteam.entity.Species;

import java.util.List;

/**
 * Service layer for data about specieses.
 *
 * Created by jan.novak
 */
public interface SpeciesService {
    /**
     * Creates new Species.
     *
     * @param species new Species
     */
    void createSpecies(Species species);

    /**
     * Deletes existing species.
     *
     * @param species species to remove
     */
    void deleteSpecies(Species species);

    /**
     * UPdates species.
     *
     * @param species species to update
     */
    void updateSpecies(Species species);

    /**
     * Finds species by its id.
     *
     * @param id
     * @return Species if it exists, NULL otherwise
     */
    Species getSpeciesById(long id);

    /**
     * Returns all existing specieses.
     *
     * @return List with all existing specieses.
     */
    List<Species> getAllSpecieses();
}
