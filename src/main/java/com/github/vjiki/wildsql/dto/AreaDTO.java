package com.github.vjiki.wildsql.dto;

import com.github.vjiki.wildsql.model.Area;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDTO {
    private Long id;
    private String name;
    private String code;

    private Double areaSquare;
    private String personName;
    private String personPhoneNumber;
}
