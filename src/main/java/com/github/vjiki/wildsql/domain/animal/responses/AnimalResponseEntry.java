package com.github.vjiki.wildsql.domain.animal.responses;

import com.github.vjiki.wildsql.domain.animal.models.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class AnimalResponseEntry {
    Animal animal;
    String areaName;
    String animalTypeName;
}
