package com.github.vjiki.wildsql.repo;

import com.github.vjiki.wildsql.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findByAreaName(String areaName);

    Page<Area> findAll(Pageable pageable);

    Page<Area> findByAreaName(String areaName, Pageable pageable);

    Area findById(long id);
}
