package com.github.vjiki.wildsql.model.repositories;

import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.model.AnimalType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Page<Animal> findAll(Pageable pageable);

    Animal findByName(String name);
}