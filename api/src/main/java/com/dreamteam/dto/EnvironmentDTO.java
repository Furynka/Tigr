package com.dreamteam.dto;

/**
 * @author Eva Ambrusova
 */
public class EnvironmentDTO {
    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + id;

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null){
            return false;
        }
        if (!(o instanceof EnvironmentDTO)){
            return false;
        }

        EnvironmentDTO other = (EnvironmentDTO) o;

        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.getName())){
            return false;
        }

        if (id != other.id){
            return false;
        }
        return true;
    }
}