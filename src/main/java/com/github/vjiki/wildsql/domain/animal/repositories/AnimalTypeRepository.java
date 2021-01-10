package com.github.vjiki.wildsql.domain.animal.repositories;

import com.github.vjiki.wildsql.domain.animal.entities.AnimalType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, Long> {
    AnimalType findByName(String name);

    Page<AnimalType> findAll(Pageable pageable);
}
