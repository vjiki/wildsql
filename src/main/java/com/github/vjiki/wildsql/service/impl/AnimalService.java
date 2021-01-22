package com.github.vjiki.wildsql.service.impl;

import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.dto.AnimalDto;
import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.model.AnimalType;
import com.github.vjiki.wildsql.model.repositories.impl.AnimalRepository;
import com.github.vjiki.wildsql.model.Area;
import com.github.vjiki.wildsql.service.common.AbstractService;
import com.github.vjiki.wildsql.service.common.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class AnimalService extends AbstractService<Animal, AnimalDto, AnimalRepository> implements InterfaceService<Animal, AnimalDto> {

    @Autowired
    private DtoConverter dtoConverter;

    public AnimalService(AnimalRepository repository) {
        super(repository);
    }

    @Override
    protected Class<Animal> getEClass() {
        return Animal.class;
    }

    @Transactional
    public Animal update(AnimalDto animalDTO) {

        Animal animal = dtoConverter.simpleConvert(animalDTO,Animal.class);

        Animal existingAnimal = preUpdate(animal.getId(), animal.getName());

        if (animal.getName() != null) {
            existingAnimal.setName(animal.getName());
        }

        if (animal.getAnimalType() == null) {
            final String message = AnimalType.class + " id " + animalDTO.getAnimalTypeId();
            throw new EntityNotFoundException(message);
        }

        if (animal.getArea() == null) {
            final String message = Area.class + " id " + animalDTO.getAreaId();
            throw new EntityNotFoundException(message);
        }

        existingAnimal.setAnimalType(animal.getAnimalType());

        existingAnimal.setArea(animal.getArea());

        return repository.save(existingAnimal);
    }

    @Override
    @Transactional
    public Animal create(AnimalDto animalDTO) {
        Animal animal = dtoConverter.simpleConvert(animalDTO,Animal.class);

        Animal uniqueNameAnimal = repository.findByName(animal.getName());
        if (uniqueNameAnimal != null) {
            final String message = Animal.class + ": Animal with name "
                    + "'" + animal.getName() + "'"
                    + " already exists with id: "
                    + "'" + uniqueNameAnimal.getId() + "'";
            throw new DataIntegrityViolationException(message);
        }

        if (animal.getAnimalType() == null) {
            final String message = AnimalType.class + " id " + animalDTO.getAnimalTypeId();
            throw new EntityNotFoundException(message);
        }

        if (animal.getArea() == null) {
            final String message = Area.class + " id " + animalDTO.getAreaId();
            throw new EntityNotFoundException(message);
        }

        return repository.save(animal);
    }
}