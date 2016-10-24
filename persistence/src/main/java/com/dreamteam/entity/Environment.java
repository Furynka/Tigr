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

    public int getEnvironmentId(Environment e){ return this.id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
