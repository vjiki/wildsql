package com.github.vjiki.wildsql.domain.animal.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.vjiki.wildsql.domain.animal.constants.AnimalClassEnum;
import com.github.vjiki.wildsql.domain.animal.constants.GroupOfPopulationEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity // This tells Hibernate to make a table out of this class
@NoArgsConstructor
@AllArgsConstructor
@Table(name="animal_types")
public class AnimalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private AnimalClassEnum animalClass;

    private GroupOfPopulationEnum groupOfPopulation;

    @Size(max = 255)
    @Column(name="animal_type_name", unique = true, nullable = false)
    private String name;


    @OneToMany(mappedBy = "animalType", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Animal> animals;

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

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;

        for(Animal a: animals) {
            a.setAnimalType(this);
        }
    }

}
