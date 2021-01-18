package com.github.vjiki.wildsql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalCreateDTO {
    private String name;

    private Long areaId;

    private Long animalTypeId;
}