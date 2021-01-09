package com.github.vjiki.wildsql.models;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity // This tells Hibernate to make a table out of this class
@Table(name="animal_types")
public class AnimalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Enumerated(EnumType.STRING)
    private AnimalClassEnum animalClass;

    //@Enumerated(EnumType.STRING)
    private GroupOfPopulationEnum groupOfPopulation;

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

    public AnimalClassEnum getAnimalClass() {
        return animalClass;
    }

    public void setAnimalClass(AnimalClassEnum animalClass) {
        this.animalClass = animalClass;
    }

    public GroupOfPopulationEnum getGroupOfPopulation() {
        return groupOfPopulation;
    }

    public void setGroupOfPopulation(GroupOfPopulationEnum groupOfPopulation) {
        this.groupOfPopulation = groupOfPopulation;
    }

    public AnimalType(AnimalClassEnum animalClass, GroupOfPopulationEnum groupOfPopulation, @Size(max = 255) String name) {
        this.animalClass = animalClass;
        this.groupOfPopulation = groupOfPopulation;
        this.name = name;
    }

    public AnimalType() {
    }
}
