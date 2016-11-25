package com.dreamteam.facade;

import com.dreamteam.dto.SpeciesDTO;

import java.util.List;

/**
 * Facade layer for data about specieses.
 *
 * Created by jan.novak
 */
public interface SpeciesFacade {
    /**
     * Creates new Species.
     *
     * @param speciesDTO new speciesDTO
     */
    void createSpecies(SpeciesDTO speciesDTO);

    /**
     * Deletes existing species.
     *
     * @param speciesId id of species to remove
     */
    void deleteSpecies(long speciesId);

    /**
     * Updates existing species.
     *
     * @param speciesId id of species to update
     * @param newName   new value of name
     */
    void changeSpeciesName(long speciesId, String newName);

    /**
     * Updates existing Species entity.
     *
     * @param speciesId      id of species to update
     * @param newDescription new value of description
     */
    void changeSpeciesDescription(long speciesId, String newDescription);

    /**
     * Sets existing Species as in danger.
     *
     * @param speciesId id of species to mark as in danger
     */
    void setSpeciesInDanger(long speciesId);

    /**
     * Sets existing Species as not in danger.
     *
     * @param speciesId id of species to mark as not in danger
     */
    void setSpeciesNotInDanger(long speciesId);

    /**
     * Adds specified animal into species and overrides previous link.
     *
     * @param animalId
     * @param speciesId
     */
    void addAnimalIntoSpecies(long animalId, long speciesId);

    /**
     * Removes specified animal from species.
     *
     * @param animalId
     * @param speciesId
     */
    void removeAnimalFromSpecies(long animalId, long speciesId);

    /**
     * Finds species by its id.
     *
     * @param id
     * @return Species if it exists, NULL otherwise
     */
    SpeciesDTO getSpeciesById(long id);

    /**
     * Returns all existing specieses.
     *
     * @return List with all existing specieses.
     */
    List<SpeciesDTO> getAllSpecieses();
}
