package com.github.vjiki.wildsql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnimalDTOResponse {
    Long id;
    String name;
    String areaName;
    String animalTypeName;
}