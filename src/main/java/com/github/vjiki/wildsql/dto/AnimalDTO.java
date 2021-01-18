package com.github.vjiki.wildsql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalDTO {
    private Long id;
    private String name;

    private Long areaId;
    private String areaName;

    private Long animalTypeId;
    private String animalTypeName;
}
