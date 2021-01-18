package com.github.vjiki.wildsql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaCreateDTO {
    private String name;
    private String code;

    private Double areaSquare;
    private String personName;
    private String personPhoneNumber;
}
