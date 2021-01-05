package com.github.vjiki.wildsql.repo;

import com.github.vjiki.wildsql.models.TerritoryArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TerritoryAreaRepository extends JpaRepository<TerritoryArea, Long> {
    List<TerritoryArea> findByAreaName(String areaName);

    Page<TerritoryArea> findAll(Pageable pageable);

    Page<TerritoryArea> findByAreaName(String areaName, Pageable pageable);

    TerritoryArea findById(long id);
}
