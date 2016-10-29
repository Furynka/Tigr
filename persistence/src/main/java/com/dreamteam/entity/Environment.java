package com.dreamteam.entity;

import javax.persistence.*;
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

    private String name;

    //TODO: namiesto String bude vytvoreny novy Typ Description
    private String description;


    public Environment() {}

    public Environment(int envId){ this.id = envId; }

    public int getEnvironmentId(){ return this.id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
