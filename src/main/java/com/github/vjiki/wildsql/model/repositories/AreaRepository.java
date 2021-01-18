package com.github.vjiki.wildsql.model.repositories;

import com.github.vjiki.wildsql.model.AnimalType;
import com.github.vjiki.wildsql.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {
    Page<Area> findAll(Pageable pageable);

    Area findByName(String name);
}
