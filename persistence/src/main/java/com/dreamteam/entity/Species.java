package com.dreamteam.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan.novak
 */
@Entity
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = "species")
    private List<Animal> animals = new ArrayList<>();

    private boolean inDanger;

    public Species() {
    }

    public Species(String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
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
        if (!(o instanceof Species)) return false;

        Species species = (Species) o;

        if (isInDanger() != species.isInDanger()) return false;
        if (getId() != null ? !getId().equals(species.getId()) : species.getId() != null) return false;
        if (!getName().equals(species.getName())) return false;
        if (getDescription() != null ? !getDescription().equals(species.getDescription()) : species.getDescription() != null)
            return false;
        return getAnimals() != null ? getAnimals().equals(species.getAnimals()) : species.getAnimals() == null;

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
}
