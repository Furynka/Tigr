package com.dreamteam.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Eva Ambrusova
 */
public class EnvironmentDTO {
    private Long id;
    private String name;
    private String description;
    private List<AnimalDTO> animalsLiving = new ArrayList<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addAnimal(AnimalDTO animal) {
        this.animalsLiving.add(animal);
    }

    public List<AnimalDTO> getAnimals() {
        return Collections.unmodifiableList(animalsLiving);
    }

    public void setAnimals(List<AnimalDTO> animals) {
        this.animalsLiving = animals;
    }

    public void removeAnimal(AnimalDTO animalLiving) { this.animalsLiving.remove(animalLiving); }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getAnimals() != null ? getAnimals().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnvironmentDTO)) return false;

        EnvironmentDTO env = (EnvironmentDTO) o;

        if (getId() != null ? !getId().equals(env.getId()) : env.getId() != null) return false;
        if (!getName().equals(env.getName())) return false;
        if (getDescription() != null ? !getDescription().equals(env.getDescription()) : env.getDescription() != null)
            return false;
        return getAnimals() != null ?
                getAnimals().size() == env.getAnimals().size()
                        && getAnimals().containsAll(env.getAnimals())
                : env.getAnimals() == null;
    }
}
