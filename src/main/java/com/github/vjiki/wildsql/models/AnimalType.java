package com.github.vjiki.wildsql.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class AnimalType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private AnimalClass animalClass;
    private GroupOfPopulation groupOfPopulation;

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

    public AnimalClass getAnimalClass() {
        return animalClass;
    }

    public void setAnimalClass(AnimalClass animalClass) {
        this.animalClass = animalClass;
    }

    public GroupOfPopulation getGroupOfPopulation() {
        return groupOfPopulation;
    }

    public void setGroupOfPopulation(GroupOfPopulation groupOfPopulation) {
        this.groupOfPopulation = groupOfPopulation;
    }

    public AnimalType(String name, AnimalClass animalClass, GroupOfPopulation groupOfPopulation) {
        this.name = name;
        this.animalClass = animalClass;
        this.groupOfPopulation = groupOfPopulation;
    }

    public AnimalType() {
    }
}
