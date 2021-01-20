package com.github.vjiki.wildsql.model.repositories.impl;

import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.model.repositories.common.AbstractRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnimalRepository extends AbstractRepository<Animal> {
}