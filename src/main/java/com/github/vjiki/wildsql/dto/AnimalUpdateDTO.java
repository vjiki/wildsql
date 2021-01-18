package com.github.vjiki.wildsql.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalUpdateDTO {
    private Long id;

    private String name;

    private Long areaId;

    private Long animalTypeId;
}
