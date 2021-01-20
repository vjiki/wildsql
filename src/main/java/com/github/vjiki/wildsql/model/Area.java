package com.github.vjiki.wildsql.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.vjiki.wildsql.model.repositories.common.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name="areas")
public class Area extends AbstractEntity {

    @Size(max = 32)
    @Column(name="area_code")
    private String code;

    @Column(name="area_square")
    private Double areaSquare;

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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @EqualsAndHashCode.Exclude
    private Set<Animal> animals;
}
