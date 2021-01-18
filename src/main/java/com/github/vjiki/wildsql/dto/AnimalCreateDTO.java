package com.github.vjiki.wildsql.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AnimalCreateDTO {
    @NotEmpty
    @Size(
            min = 2,
            max = 255,
            message = "The Animal name '${validatedValue}' must be between {min} and {max} characters long"
    )
    private String name;

    @NotNull
    @Min(
            value = 0,
            message = "There must be at least {value}"
    )
    private Long areaId;

    @NotNull
    @Min(
            value = 0,
            message = "There must be at least {value}"
    )
    private Long animalTypeId;
}