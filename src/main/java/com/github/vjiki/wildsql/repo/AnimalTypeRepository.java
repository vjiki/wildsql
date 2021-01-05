package com.github.vjiki.wildsql.repo;

import com.github.vjiki.wildsql.models.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, Long> {
    AnimalType findByName(String name);
}
