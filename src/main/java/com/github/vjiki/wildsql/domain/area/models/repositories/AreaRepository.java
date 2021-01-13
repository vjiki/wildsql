package com.github.vjiki.wildsql.domain.area.models.repositories;

import com.github.vjiki.wildsql.domain.area.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AreaRepository extends JpaRepository<Area, Long> {
    Page<Area> findAll(Pageable pageable);
}
