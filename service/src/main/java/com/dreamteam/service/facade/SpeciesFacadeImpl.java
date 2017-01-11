package com.dreamteam.service.facade;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.dto.SpeciesDTO;
import com.dreamteam.entity.Animal;
import com.dreamteam.entity.Species;
import com.dreamteam.exceptions.TigrAPIException;
import com.dreamteam.facade.SpeciesFacade;
import com.dreamteam.service.AnimalService;
import com.dreamteam.service.BeanMappingService;
import com.dreamteam.service.SpeciesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan.novak
 */
@Service
@Transactional
public class SpeciesFacadeImpl implements SpeciesFacade {

    @Inject
    private SpeciesService speciesService;
    @Inject
    private AnimalService animalService;
    @Inject
    private BeanMappingService mappingService;

    @Override
    public void createSpecies(SpeciesDTO speciesDTO) {
        speciesService.createSpecies(convertDtoToSpeciesForInitSave(speciesDTO));
    }

    @Override
    public void deleteSpecies(long speciesId) {
        speciesService.deleteSpecies(getSpeciesByIdOrFailIfNotFound(speciesId));
    }

    @Override
    public void changeSpeciesName(long speciesId, String newName) {
        Species species = getSpeciesByIdOrFailIfNotFound(speciesId);
        species.setName(newName);
        speciesService.updateSpecies(species);
    }

    @Override
    public void changeSpeciesDescription(long speciesId, String newDescription) {
        Species species = getSpeciesByIdOrFailIfNotFound(speciesId);
        species.setDescription(newDescription);
        speciesService.updateSpecies(species);
    }

    @Override
    public void setSpeciesInDanger(long speciesId) {
        Species species = getSpeciesByIdOrFailIfNotFound(speciesId);
        species.setInDanger(Boolean.TRUE);
        speciesService.updateSpecies(species);
    }

    @Override
    public void setSpeciesNotInDanger(long speciesId) {
        Species species = getSpeciesByIdOrFailIfNotFound(speciesId);
        species.setInDanger(Boolean.FALSE);
        speciesService.updateSpecies(species);
    }

    @Override
    public void addAnimalIntoSpecies(long animalId, long speciesId) {
        Species species = getSpeciesByIdOrFailIfNotFound(speciesId);
        Animal animal = animalService.findById(animalId);

        //store into animal - owning side
        animal.setSpecies(species);
        animalService.update(animal);

        //store into collection - inverse side
        species.addAnimal(animal);
        speciesService.updateSpecies(species);
    }

    @Override
    public void removeAnimalFromSpecies(long animalId, long speciesId) {
        Species species = getSpeciesByIdOrFailIfNotFound(speciesId);
        Animal animal = animalService.findById(animalId);

        //store into animal - owning side
        animal.setSpecies(null);
        animalService.update(animal);

        //store into collection - inverse side
        species.removeAnimal(animal);
        speciesService.updateSpecies(species);
    }

    @Override
    @Transactional(readOnly = true)
    public SpeciesDTO getSpeciesById(long id) {
        return convertSpeciesToDto(getSpeciesByIdOrFailIfNotFound(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SpeciesDTO> getAllSpecieses() {
        return convertSpeciesListToDtosList(speciesService.getAllSpecieses());
    }

    private Species getSpeciesByIdOrFailIfNotFound(long id) {
        Species species = speciesService.getSpeciesById(id);
        if (species == null)
            throw new TigrAPIException("Cannot found species with ID: " + id);
        return species;
    }

    private Species convertDtoToSpeciesForInitSave(SpeciesDTO dto) {
        Species species = new Species();
        species.setId(dto.getId());
        species.setName(dto.getName());
        species.setDescription(dto.getDescription());
        species.setInDanger(dto.isInDanger());
        return species;
    }

    private SpeciesDTO convertSpeciesToDto(Species species) {
        SpeciesDTO dto = new SpeciesDTO();
        dto.setId(species.getId());
        dto.setName(species.getName());
        dto.setDescription(species.getDescription());
        dto.setAnimals(mappingService.mapTo(species.getAnimals(), AnimalDTO.class));
        dto.setInDanger(species.isInDanger());
        return dto;
    }

    private List<SpeciesDTO> convertSpeciesListToDtosList(List<Species> speciesList) {
        List<SpeciesDTO> dtos = new ArrayList<>();
        for (Species species : speciesList) {
            dtos.add(convertSpeciesToDto(species));
        }
        return dtos;
    }
}
