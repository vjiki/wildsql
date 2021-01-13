package com.github.vjiki.wildsql.domain.animal.models.repositories;

import com.github.vjiki.wildsql.domain.animal.models.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Page<Animal> findAll(Pageable pageable);
}