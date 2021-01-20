package com.github.vjiki.wildsql.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import com.github.vjiki.wildsql.model.repositories.common.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data // possibly needs to avoid use of @data annotation for entity class
//@Getter
//@Setter
//@EqualsAndHashCode
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Table(name="animals")
public class Animal extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @EqualsAndHashCode.Exclude
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "animal_type_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @EqualsAndHashCode.Exclude
    private AnimalType animalType;
}
