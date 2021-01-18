package com.github.vjiki.wildsql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaCreateDTO {

    @NotEmpty
    @Size(
            min = 1,
            max = 255,
            message = "The area name '${validatedValue}' must be between {min} and {max} characters long"
    )
    private String name;

    @NotEmpty
    @Size(
            min = 1,
            max = 10,
            message = "The area code '${validatedValue}' must be between {min} and {max} characters long"
    )
    private String code;

    @NotNull
    @DecimalMin(
            value = "1",
            message = "The Area square ${formatter.format('%1$.2f', validatedValue)} must be higher than {1}%"
    )
    private Double areaSquare;

    @NotEmpty
    @Size(
            min = 1,
            max = 255,
            message = "The area person name '${validatedValue}' must be between {min} and {max} characters long"
    )
    private String personName;

    @NotEmpty
    @Size(
            min = 1,
            max = 255,
            message = "The person phone '${validatedValue}' must be between {min} and {max} characters long"
    )
    private String personPhoneNumber;
}
