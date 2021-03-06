package com.github.vjiki.wildsql.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.vjiki.wildsql.constants.AnimalClassEnum;
import com.github.vjiki.wildsql.constants.GroupOfPopulationEnum;
import com.github.vjiki.wildsql.model.repositories.common.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity // This tells Hibernate to make a table out of this class
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="animal_types")
public class AnimalType extends AbstractEntity {

    private AnimalClassEnum animalClass;

    private GroupOfPopulationEnum groupOfPopulation;

    @OneToMany(mappedBy = "animalType", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Animal> animals = new HashSet<>();
}
