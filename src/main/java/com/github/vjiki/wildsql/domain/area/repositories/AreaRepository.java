package com.github.vjiki.wildsql.domain.area.repositories;

import com.github.vjiki.wildsql.domain.area.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    //@TODO: remove
    List<Area> findByAreaName(String areaName);
    Page<Area> findByAreaName(String areaName, Pageable pageable);


    Page<Area> findAll(Pageable pageable);
}
