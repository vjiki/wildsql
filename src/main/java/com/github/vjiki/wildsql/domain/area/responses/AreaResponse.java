package com.github.vjiki.wildsql.domain.area.responses;

import com.github.vjiki.wildsql.domain.animal.models.Animal;
import com.github.vjiki.wildsql.domain.area.models.Area;
import com.github.vjiki.wildsql.domain.animal.constants.GroupOfPopulationEnum;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

@NoArgsConstructor
public class AreaResponse {

    private Page<AreaResponseEntry> pageAreas;

    @NoArgsConstructor
    private class AreaResponseEntry {
        Area area;
        Map<String, Object> groups = new HashMap<>();
        Map<String, Integer> animalNumbers = new HashMap<>();

        AreaResponseEntry (Area area, Map<String, Object> groups, Map<String, Integer> animalNumbers) {
            this.area = area;
            this.groups = groups;
            this.animalNumbers = animalNumbers;
        }

        public Area getArea() {
            return area;
        }

        public void setArea(Area area) {
            this.area = area;
        }

        public Map<String, Object> getGroups() {
            return groups;
        }

        public void setGroups(Map<String, Object> groups) {
            this.groups = groups;
        }

        public Map<String, Integer> getAnimalNumbers() {
            return animalNumbers;
        }

        public void setAnimalNumbers(Map<String, Integer> animalNumbers) {
            this.animalNumbers = animalNumbers;
        }
    }

    public AreaResponse(Page<Area> pageAreas, Pageable pageable) {

        List<AreaResponse.AreaResponseEntry> areaResponseEntryList = new ArrayList<>();
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
            AreaResponse.AreaResponseEntry areaResponseEntry = new AreaResponse.AreaResponseEntry(area, groups, animalNumbers);
            areaResponseEntryList.add(areaResponseEntry);
        }

        this.pageAreas = new PageImpl<>(areaResponseEntryList, pageable, areaResponseEntryList.size());
    }

    public Page<AreaResponseEntry> getPageAreas() {
        return pageAreas;
    }

    public void setPageAreas(Page<AreaResponseEntry> pageAreas) {
        this.pageAreas = pageAreas;
    }
}