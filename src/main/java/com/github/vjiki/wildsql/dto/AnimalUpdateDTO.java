package com.github.vjiki.wildsql.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AnimalUpdateDTO {

    @Id
    @NotNull
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Long areaId;

    @NotNull
    private Long animalTypeId;
}
