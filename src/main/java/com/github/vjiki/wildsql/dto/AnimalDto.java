package com.github.vjiki.wildsql.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AnimalDto extends AbstractDto {

    @JsonIgnore
    private AnimalTypeDto animalTypeDto;
    @JsonIgnore
    private AreaDto areaDto;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Min(
            value = 0,
            message = "The min is {value}"
    )
    private Long areaId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String areaName;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Min(
            value = 0,
            message = "The min is {value}"
    )
    private Long animalTypeId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String animalTypeName;
}
