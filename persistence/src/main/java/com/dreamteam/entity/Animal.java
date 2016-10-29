package com.dreamteam.entity;

import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


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
    
    private Set<Enviroment> enviroments = new HashSet<>();

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
    
    public Set<Enviroment> getEnviroments() {
        return enviroments;
    }
    
    public void setEnviroments(Set<Enviroment> enviroments) {
        this.enviroments = enviroments;
    }
}