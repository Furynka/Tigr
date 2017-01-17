package com.dreamteam.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan.novak
 */
public class SpeciesDTO {

    private Long id;
	@NotNull
	@Size(min = 5)
	private String name;
	@NotNull
	private String description;
	private List<AnimalDTO> animals = new ArrayList<>();
    private boolean inDanger;

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

    public List<AnimalDTO> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalDTO> animals) {
        this.animals = animals;
    }

    public boolean isInDanger() {
        return inDanger;
    }

    public void setInDanger(boolean inDanger) {
        this.inDanger = inDanger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpeciesDTO)) return false;

        SpeciesDTO that = (SpeciesDTO) o;

        if (isInDanger() != that.isInDanger()) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (!getName().equals(that.getName())) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        return getAnimals() != null ? getAnimals().equals(that.getAnimals()) : that.getAnimals() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getAnimals() != null ? getAnimals().hashCode() : 0);
        result = 31 * result + (isInDanger() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpeciesDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", animals=" + animals +
                ", inDanger=" + inDanger +
                '}';
    }
}
