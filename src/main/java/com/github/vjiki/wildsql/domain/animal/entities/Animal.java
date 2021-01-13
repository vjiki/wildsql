package com.github.vjiki.wildsql.domain.animal.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.vjiki.wildsql.domain.area.entities.Area;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="animals")
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name="animal_name", unique = true, nullable = false)
    private String name;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "animal_type_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AnimalType animalType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Area getArea() {
        return area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }
}
