package com.github.vjiki.wildsql.domain.area.responses;

import com.github.vjiki.wildsql.domain.area.models.Area;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
class AreaResponseEntry {
    Area area;
    Map<String, Object> groups = new HashMap<>();
    Map<String, Integer> animalNumbers = new HashMap<>();
}
