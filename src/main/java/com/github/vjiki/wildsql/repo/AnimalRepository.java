package com.github.vjiki.wildsql.repo;

import com.github.vjiki.wildsql.models.Animal;
import com.github.vjiki.wildsql.models.Area;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByArea(Area area, Sort sort);
}
