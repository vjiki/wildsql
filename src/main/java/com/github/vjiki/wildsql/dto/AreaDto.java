package com.github.vjiki.wildsql.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.vjiki.wildsql.model.Area;
import com.github.vjiki.wildsql.validators.ValidPhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AreaDto extends AbstractDto {

    @NotEmpty
    @NotBlank
    @Size(
            max = 10,
            message = "The area code '${validatedValue}' must be max {max} characters long"
    )
    private String code;

    @NotNull
    @DecimalMin(
            value = "1",
            message = "The Area square ${formatter.format('%1$.2f', validatedValue)} must be higher than {1}%"
    )
    private Double areaSquare;

    @NotEmpty
    @NotBlank
    @Size(
            max = 255,
            message = "The area person name '${validatedValue}' must be max {max} characters long"
    )
    private String personName;

    @NotEmpty
    @NotBlank
    @ValidPhoneNumber(message="Please enter a valid phone number")
    @Size(
            max = 255,
            message = "The person phone '${validatedValue}' must be max {max} characters long"
    )
    private String personPhoneNumber;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Map<String, Integer> animalNumbers = new HashMap<>();
}
