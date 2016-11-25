package com.dreamteam.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private Set<AnimalDTO> preys = new HashSet<>();
    private Set<AnimalDTO> predators = new HashSet<>();
    private Set<EnvironmentDTO> environments = new HashSet<>();

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

    public void setCount(int count) { this.count = count; }

    public int getCount() { return this.count; }

    public Set<AnimalDTO> getPreys() {
        return preys;
    }

    public void setPreys(Set<AnimalDTO> preys) {
        this.preys = preys;
    }

    public Set<AnimalDTO> getPredators() {
        return predators;
    }

    public void setPredators(Set<AnimalDTO> predators) {
        this.predators = predators;
    }

    public Set<EnvironmentDTO> getEnvironments() {
        return environments;
    }

    public void setEnvironments(Set<EnvironmentDTO> environments) {
        this.environments = environments;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AnimalDTO other = (AnimalDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
