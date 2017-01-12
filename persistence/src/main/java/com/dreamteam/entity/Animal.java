package com.dreamteam.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy="preys")
    private Set<Animal> predators = new HashSet<>();

    @ManyToMany
    private Set<Animal> preys = new HashSet<>();

    @ManyToMany
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

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
		if (this.species != null)
			this.species.removeAnimal(this);

        this.species = species;
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