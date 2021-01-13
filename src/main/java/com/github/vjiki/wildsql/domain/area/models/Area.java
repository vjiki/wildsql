package com.github.vjiki.wildsql.domain.area.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.vjiki.wildsql.domain.animal.models.Animal;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Table(name="areas")
public class Area implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="area_square")
    private Double areaSquare;

    @Size(max = 255)
    @Column(name="area_name", unique = true, nullable = false)
    private String areaName;

    @Size(max = 32)
    @Column(name="area_code")
    private String areaCode;

    @Size(max = 255)
    @Column(name="person_name")
    private String personName;

    @Size(max = 25)
    @Column(name="person_phone_number")
    private String personPhoneNumber;

    // @TODO
    // Cascade type ALL can cause performance issues
    // it can be changed to the following
    //  {CascadeType.PERSIST,CascadeType.MERGE}
    // https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql/
    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Animal> animals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAreaSquare() {
        return areaSquare;
    }

    public void setAreaSquare(Double areaSquare) {
        this.areaSquare = areaSquare;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;

        for(Animal a: animals) {
            a.setArea(this);
        }
    }
}
