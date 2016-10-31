package com.dreamteam.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing environment.
 *
 * @author Eva Ambrusova
 */
@Entity
public class Environment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @ManyToMany(mappedBy="environments")
    private Set<Animal> animalsLiving = new HashSet<>();

    public Environment() {}

    public Environment(int envId){ this.id = envId; }

    public int getEnvironmentId(){ return this.id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public void addAnimal(Animal animal) {
        this.animalsLiving.add(animal);
    }

    public Set<Animal> getAnimals() {
        return Collections.unmodifiableSet(animalsLiving);
    }

    public void removeAnimal(Animal animalLiving) { this.animalsLiving.remove(animalLiving); }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (o == null){
            return false;
        }
        if (!(o instanceof Environment)){
            return false;
        }

        Environment other = (Environment) o;

        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.getName())){
            return false;
        }

        if (id != other.getEnvironmentId()){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;

        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + id;

        return result;
    }


}
