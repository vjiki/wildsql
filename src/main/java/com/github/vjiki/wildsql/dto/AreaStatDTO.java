package com.github.vjiki.wildsql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class AreaStatDTO {
    private Long id;
    private String name;
    private String code;

    private Double areaSquare;
    private String personName;
    private String personPhoneNumber;

    private Map<String, Integer> animalNumbers = new HashMap<>();
}
