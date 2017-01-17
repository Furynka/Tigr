package com.dreamteam.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

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

    @ManyToOne
    private Species species;

    @NotNull
    private String description;

    @Column
    private int count;

    @ManyToMany(mappedBy="preys", fetch = FetchType.LAZY)
    private Set<Animal> predators = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Animal> preys = new HashSet<>();

    @ManyToMany
    private List<Environment> environments = new ArrayList<>();
    
    public Animal(){
        
    }
        
    public Animal(String name){
        this.name = name;
    }

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

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
		if (this.species != null)
			this.species.removeAnimal(this);

        this.species = species;
        if (species != null)
		    species.addAnimal(this);
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
    
    public List<Environment> getEnvironments() {
        return Collections.unmodifiableList(environments);
    }
    
    public void addEnvironment(Environment environment) {
        environments.add(environment);
    }
    
    public void removeEnvironment(Environment environment) {
        environments.remove(environment);
    }
    
    public void clearEnvironments() {
        environments.clear();
    }
    
    public void clearPredators() {
        predators.clear();
    }
    
    public void clearPreys() {
        preys.clear();
    }

    @Override
    public int hashCode() {
        final int prime = getId() != null ? getId().hashCode() : 31;
        int result = 1 * prime;
        if (getName() != null) result = prime * result + getName().hashCode();
        if (getDescription() != null) result = prime * result + getDescription().hashCode();
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
        if (!(obj instanceof Animal)){
            return false;
        }
        Animal animalObj = (Animal) obj;
        
        if (getName() != null && !getName().equals(animalObj.getName())){
            return false;
        }
        if (getId() != null && !getId().equals(animalObj.getId())){
            return false;
        }
        return true;
    }
      
}