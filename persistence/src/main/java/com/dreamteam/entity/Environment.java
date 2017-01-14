package com.dreamteam.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing environment.
 *
 * @author Eva Ambrusova
 */
@Entity
public class Environment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @ManyToMany(mappedBy="environments")
    private List<Animal> animalsLiving = new ArrayList<>();

    public Environment() {}

    public Environment(Long envId){ this.id = envId; }

    public Long getId(){ return this.id; }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Animal> getAnimals() {
        return this.animalsLiving;
    }

    public void removeAnimal(Animal animalLiving) { this.animalsLiving.remove(animalLiving); }


    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Environment)) return false;

        Environment env = (Environment) o;

        if (getId() != null ? !getId().equals(env.getId()) : env.getId() != null) return false;
        if (!getName().equals(env.getName())) return false;
        if (getDescription() != null ? !getDescription().equals(env.getDescription()) : env.getDescription() != null)
            return false;
        return getAnimals() != null ?
                getAnimals().size() == env.getAnimals().size()
                        && getAnimals().containsAll(env.getAnimals())
                : env.getAnimals() == null;
    }

    @Override
    public int hashCode(){
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getAnimals() != null ? getAnimals().hashCode() : 0);
        return result;
    }


}
