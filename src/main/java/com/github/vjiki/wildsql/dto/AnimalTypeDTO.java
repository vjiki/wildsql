package com.github.vjiki.wildsql.dto;

import com.github.vjiki.wildsql.constants.AnimalClassEnum;
import com.github.vjiki.wildsql.constants.GroupOfPopulationEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalTypeDTO {
    private Long id;
    private String name;
    private AnimalClassEnum animalClass;
    private GroupOfPopulationEnum groupOfPopulation;
}