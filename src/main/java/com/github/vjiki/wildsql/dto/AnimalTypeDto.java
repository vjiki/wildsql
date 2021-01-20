package com.github.vjiki.wildsql.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.vjiki.wildsql.constants.AnimalClassEnum;
import com.github.vjiki.wildsql.constants.GroupOfPopulationEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnimalTypeDto extends AbstractDto {

    @NotNull
    private AnimalClassEnum animalClass;

    @NotNull
    private GroupOfPopulationEnum groupOfPopulation;
}
