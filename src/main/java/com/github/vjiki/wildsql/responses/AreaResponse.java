package com.github.vjiki.wildsql.responses;

import com.github.vjiki.wildsql.models.Area;
import com.github.vjiki.wildsql.models.GroupOfPopulationEnum;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class AreaResponse {

    Page<Area> pageAreas;
    Map<String, Object> groups = new HashMap<>();

    public AreaResponse(Page<Area> pageAreas) {
        this.pageAreas = pageAreas;

        for (GroupOfPopulationEnum group : GroupOfPopulationEnum.values()) {
            groups.put(group.toString(), group.getNumberOfAnimals());
        }
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