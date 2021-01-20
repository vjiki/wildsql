package com.github.vjiki.wildsql.service.impl;

import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.dto.AnimalTypeDto;
import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.model.AnimalType;
import com.github.vjiki.wildsql.model.repositories.AbstractRepository;
import com.github.vjiki.wildsql.model.repositories.AnimalRepository;
import com.github.vjiki.wildsql.model.repositories.AnimalTypeRepository;
import com.github.vjiki.wildsql.service.common.ASingleService;
import com.github.vjiki.wildsql.service.common.ISingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AnimalTypeService extends ASingleService<AnimalType, AnimalTypeDto, AnimalTypeRepository>  implements ISingleService<AnimalType, AnimalTypeDto> {

    @Autowired
    private DtoConverter dtoConverter;

    public AnimalTypeService(AnimalTypeRepository repository) {
        super(repository);
    }

    @Override
    protected Class<AnimalType> getEClass() {
        return AnimalType.class;
    }

    @Transactional
    public AnimalType update(AnimalTypeDto animalTypeDTO) {

        AnimalType animalType = dtoConverter.simpleConvert(animalTypeDTO, AnimalType.class);

        AnimalType existingAnimalType = preUpdate(animalType.getId(), animalType.getName());

        existingAnimalType.setName(animalType.getName());
        existingAnimalType.setAnimalClass(animalType.getAnimalClass());
        existingAnimalType.setGroupOfPopulation(animalType.getGroupOfPopulation());

        return repository.save(existingAnimalType);
    }

}