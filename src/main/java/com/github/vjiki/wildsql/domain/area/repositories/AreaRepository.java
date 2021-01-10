package com.github.vjiki.wildsql.domain.area.repositories;

import com.github.vjiki.wildsql.domain.area.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    Page<Area> findAll(Pageable pageable);
}
