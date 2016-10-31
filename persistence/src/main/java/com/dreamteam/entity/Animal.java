package com.dreamteam.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.ManyToMany;


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
    @Column
    private String name;

    @NotNull
    private String species;

    @NotNull
    private String description;

    @ManyToMany
    private final Set<Animal> predators = new HashSet<>();

    @ManyToMany
    private final Set<Animal> preys = new HashSet<>();

    @ManyToMany
    private final Set<Environment> environments = new HashSet<>();

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
        return Collections.unmodifiableSet(predators);
    }

    public void addPredator(Animal predator) {
        predators.add(predator);
    }
    
    public void removePredator(Animal predator) {
        predators.remove(predator);
    }

    public Set<Animal> getPreys() {
        return Collections.unmodifiableSet(preys);
    }

    public void addPrey(Animal prey) {
        preys.add(prey);
    }
    
    public void removePrey(Animal prey) {
        preys.remove(prey);
    }
    
    public Set<Environment> getEnvironments() {
        return Collections.unmodifiableSet(environments);
    }
    
    public void addEnvironment(Environment environment) {
        environments.add(environment);
    }
    
    public void removeEnvironment(Environment environment) {
        environments.remove(environment);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
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
        return this.name.equals(other.name);
    }
      
}