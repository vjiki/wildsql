package com.github.vjiki.wildsql.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.vjiki.wildsql.constants.AnimalClassEnum;
import com.github.vjiki.wildsql.constants.GroupOfPopulationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity // This tells Hibernate to make a table out of this class
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="animal_types")
public class AnimalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name="animal_type_name", unique = true, nullable = false)
    private String name;

    private AnimalClassEnum animalClass;

    private GroupOfPopulationEnum groupOfPopulation;

    @OneToMany(mappedBy = "animalType", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @EqualsAndHashCode.Exclude
    private Set<Animal> animals;
}
