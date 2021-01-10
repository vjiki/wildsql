package com.github.vjiki.wildsql.domain.animal.repositories;

import com.github.vjiki.wildsql.domain.animal.entities.Animal;
import com.github.vjiki.wildsql.domain.area.entities.Area;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByArea(Area area, Sort sort);
}
