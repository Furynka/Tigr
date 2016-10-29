package com.dreamteam.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import com.dreamteam.entity.Environment;


/**
 * Entity class representing animals.
 *
 * @author Jiri Oliva
 */
@Entity
public class Animal{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String species;

    @NotNull
    private String description;

    private Set<Animal> predators = new HashSet<>();

    private Set<Animal> preys = new HashSet<>();

    private Set<Environment> environments = new HashSet<>();

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

    public Set<Animal> getPredators() {
        return predators;
    }

    public void setPredators(Set<Animal> predators) {
        this.predators = predators;
    }

    public Set<Animal> getPreys() {
        return preys;
    }

    public void setPreys(Set<Animal> preys) {
        this.preys = preys;
    }
    
    public Set<Environment> getEnvironments() {
        return environments;
    }
    
    public void setEnviroments(Set<Environment> environments) {
        this.environments = environments;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
      
}