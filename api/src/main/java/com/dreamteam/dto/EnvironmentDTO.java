package com.dreamteam.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Eva Ambrusova
 */
public class EnvironmentDTO {
    private Long id;
    private String name;
    private String description;
    private Set<AnimalDTO> animalsLiving = new HashSet<>();

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

    public Set<AnimalDTO> getAnimals() {
        return Collections.unmodifiableSet(animalsLiving);
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

	@Override
	public String toString() {
		return "EnvironmentDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
