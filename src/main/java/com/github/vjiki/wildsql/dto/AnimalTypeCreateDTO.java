package com.github.vjiki.wildsql.dto;

import com.github.vjiki.wildsql.constants.AnimalClassEnum;
import com.github.vjiki.wildsql.constants.GroupOfPopulationEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AnimalTypeCreateDTO {
    @NotEmpty
    @Size(
            min = 1,
            max = 255,
            message = "The Animal Type Name '${validatedValue}' must be between {min} and {max} characters long"
    )
    private String name;

    @NotNull
    private AnimalClassEnum animalClass;

    @NotNull
    private GroupOfPopulationEnum groupOfPopulation;
}