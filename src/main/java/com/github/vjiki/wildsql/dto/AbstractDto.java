package com.github.vjiki.wildsql.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractDto implements Serializable {
    @Min(
            value = 0,
            message = "The min is {value}"
    )
    private Long id;

    @NotEmpty
    @NotBlank
    @Size(
            max = 255,
            message = "The name '${validatedValue}' must be max {max} characters long"
    )
    private String name;
}