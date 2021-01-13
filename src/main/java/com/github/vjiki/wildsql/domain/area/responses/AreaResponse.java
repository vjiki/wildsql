package com.github.vjiki.wildsql.domain.area.responses;

import com.github.vjiki.wildsql.domain.animal.models.Animal;
import com.github.vjiki.wildsql.domain.area.models.Area;
import com.github.vjiki.wildsql.domain.animal.constants.GroupOfPopulationEnum;
import org.springframework.data.domain.Page;

import java.util.*;


public class AreaResponse {

    Page<Area> pageAreas;
    Map<String, Object> groups = new HashMap<>();
    Map<String, Map<String,Integer>> types = new HashMap<>();


    public AreaResponse(Page<Area> pageAreas) {
        this.pageAreas = pageAreas;

        for (GroupOfPopulationEnum group : GroupOfPopulationEnum.values()) {
            groups.put(group.toString(), group.getNumberOfAnimals());
        }


        for (Area area : pageAreas.getContent()) {
            Map<String, Integer> animalNumbers = new HashMap<>();
            for (Animal animal : area.getAnimals()) {
                String animalTypeName = animal.getAnimalType().getName();
                animalNumbers.merge(animalTypeName, 1, Integer::sum);
            }
            types.put(area.getAreaName(), animalNumbers);
        }
    }

    public Map<String, Map<String, Integer>> getTypes() {
        return types;
    }

    public void setTypes(Map<String, Map<String, Integer>> types) {
        this.types = types;
    }

    public Page<Area> getPageAreas() {
        return pageAreas;
    }

    public void setPageAreas(Page<Area> pageAreas) {
        this.pageAreas = pageAreas;
    }

    public Map<String, Object> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, Object> groups) {
        this.groups = groups;
    }
}