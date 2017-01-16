package com.dreamteam.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for Animal entity
 * 
 * @author Jiri Oliva
 */
public class AnimalDTO {
    private Long id;
    private String name;
    private String species;
    private String description;
    private int count;
    private List<AnimalDTO> preys = new ArrayList<>();
    private List<AnimalDTO> predators = new ArrayList<>();
    private List<EnvironmentDTO> environments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() { return this.count; }

	public void setCount(int count) {
		this.count = count;
	}

    public List<AnimalDTO> getPreys() {
        return preys;
    }

    public void setPreys(List<AnimalDTO> preys) {
        this.preys = preys;
    }

    public List<AnimalDTO> getPredators() {
        return predators;
    }

    public void setPredators(List<AnimalDTO> predators) {
        this.predators = predators;
    }

    public List<EnvironmentDTO> getEnvironments() {
        return environments;
    }

    public void setEnvironments(List<EnvironmentDTO> environments) {
        this.environments = environments;
    }

    @Override
    public int hashCode() {
        final int prime = getId() != null ? getId().hashCode() : 31;
        int result = 1;
        result = prime * result + /*getName().hashCode()*/ (getName() != null ? getName().hashCode() : 0);
        result = prime * result + /*getDescription().hashCode()*/ (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AnimalDTO)){
            return false;
        }
        AnimalDTO animalObj = (AnimalDTO) obj;
        
        if (getName() != null && !getName().equals(animalObj.getName())){
            return false;
        }
        if (getId() != null && !getId().equals(animalObj.getId())){
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "AnimalDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", species='" + species + '\'' +
				", description='" + description + '\'' +
				", count=" + count +
				", preys=" + preys +
				", predators=" + predators +
				", environments=" + environments +
				'}';
	}
}
