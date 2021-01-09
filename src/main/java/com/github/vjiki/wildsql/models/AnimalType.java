package com.github.vjiki.wildsql.models;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity // This tells Hibernate to make a table out of this class
@Table(name="animal_types")
public class AnimalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AnimalClass animalClass;

    @Enumerated(EnumType.STRING)
    private GroupOfPopulation groupOfPopulation;

    @Size(max = 255)
    @Column(name="animal_type_name", unique = true, nullable = false)
    private String name;

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
