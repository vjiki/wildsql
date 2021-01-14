package com.github.vjiki.wildsql.domain.area.responses;

import com.github.vjiki.wildsql.domain.animal.models.Animal;
import com.github.vjiki.wildsql.domain.area.models.Area;
import com.github.vjiki.wildsql.domain.animal.constants.GroupOfPopulationEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

@Data
@NoArgsConstructor
public class AreaResponse {

    private Page<AreaResponseEntry> pageAreas;

    public AreaResponse(Page<Area> pageAreas, Pageable pageable) {

        List<AreaResponseEntry> areaResponseEntryList = new ArrayList<>();
        Map<String, Object> groups = new HashMap<>();

        for (GroupOfPopulationEnum group : GroupOfPopulationEnum.values()) {
            groups.put(group.toString(), group.getNumberOfAnimals());
        }

        for (Area area : pageAreas.getContent()) {
            Map<String, Integer> animalNumbers = new HashMap<>();

            for (Animal animal : area.getAnimals()) {
                String animalTypeName = animal.getAnimalType().getName();
                animalNumbers.merge(animalTypeName, 1, Integer::sum);
            }
            AreaResponseEntry areaResponseEntry = new AreaResponseEntry(area, groups, animalNumbers);
            areaResponseEntryList.add(areaResponseEntry);
        }

        this.pageAreas = new PageImpl<>(areaResponseEntryList, pageable, areaResponseEntryList.size());
    }
}