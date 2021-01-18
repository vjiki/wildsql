package com.github.vjiki.wildsql.service.impl;

import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.dto.AnimalDTO;
import com.github.vjiki.wildsql.exception.MyResourceNotFoundException;
import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.model.AnimalType;
import com.github.vjiki.wildsql.model.repositories.AnimalRepository;
import com.github.vjiki.wildsql.model.Area;
import com.github.vjiki.wildsql.model.repositories.AnimalTypeRepository;
import com.github.vjiki.wildsql.model.repositories.AreaRepository;
import com.github.vjiki.wildsql.service.common.ISingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService implements ISingleService<Animal,AnimalDTO> {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private DtoConverter dtoConverter;


    @Override
    @Transactional(readOnly = true)
    public Animal getById(Long id) {
        return animalRepository.getOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);

        if (optionalAnimal.isEmpty()) {
            final String message = Animal.class + " id " + id.toString();
            throw new EntityNotFoundException(message);
        }

        animalRepository.delete(optionalAnimal.get());
    }


    @Transactional
    @Override
    public Animal update(AnimalDTO animalDTO) {

        //@TODO
        try {
            Animal animal = dtoConverter.convertToAnimalEntity(animalDTO);

            Animal existingAnimal = animalRepository.getOne(animal.getId());

            Animal uniqueNameAnimal = animalRepository.findByName(animal.getName());
            if (uniqueNameAnimal != null) {
                final String message = Animal.class + ": Animal with name "
                        + "'" + animal.getName() + "'"
                        + " already exists with id: "
                        + "'" + uniqueNameAnimal.getId() + "'";
                throw new DataIntegrityViolationException(message);
            }

            Optional<Area> optionalArea = areaRepository.findById(animalDTO.getAreaId());
            if (optionalArea.isEmpty()) {
                final String message = Area.class + " id " + animalDTO.getAreaId().toString();
                throw new EntityNotFoundException(message);
            }

            Optional<AnimalType> optionalAnimalType = animalTypeRepository.findById(animalDTO.getAnimalTypeId());
            if (optionalAnimalType.isEmpty()) {
                final String message = AnimalType.class + " id " + animalDTO.getAnimalTypeId().toString();
                throw new EntityNotFoundException(message);
            }

            animal.setArea(optionalArea.get());
            animal.setAnimalType(optionalAnimalType.get());

            return animalRepository.save(existingAnimal);
        } catch (ParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Parse exception", e);
        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<Animal> getList(Pageable pageable) {

        Page<Animal> animals = animalRepository.findAll(pageable);

        if (pageable.getPageNumber() > animals.getTotalPages()) {
            int pageNumber = pageable.getPageNumber();
            int totalNumber = animals.getTotalPages();
            final String message = "Page not found: "  + pageNumber + " > " + totalNumber;
            throw new MyResourceNotFoundException(message);
        }

        if (animals.isEmpty())
        {
            final String message = "Page is empty";
            throw new EntityNotFoundException(message);
        }

        return animals.getContent();
    }

    @Override
    @Transactional
    public Animal create(AnimalDTO animalDTO) {
        try {
            Animal animal = dtoConverter.convertToAnimalEntity(animalDTO);

            Animal uniqueNameAnimal = animalRepository.findByName(animal.getName());
            if (uniqueNameAnimal != null) {
                final String message = Animal.class + ": Animal with name "
                        + "'" + animal.getName() + "'"
                        + " already exists with id: "
                        + "'" + uniqueNameAnimal.getId() + "'";
                throw new DataIntegrityViolationException(message);
            }


            Optional<Area> optionalArea = areaRepository.findById(animalDTO.getAreaId());
            if (optionalArea.isEmpty()) {
                final String message = Area.class + " id " + animalDTO.getAreaId().toString();
                throw new EntityNotFoundException(message);
            }
            animal.setArea(optionalArea.get());


            Optional<AnimalType> optionalAnimalType = animalTypeRepository.findById(animalDTO.getAnimalTypeId());
            if (optionalAnimalType.isEmpty()) {
                final String message = AnimalType.class + " id " + animalDTO.getAnimalTypeId().toString();
                throw new EntityNotFoundException(message);
            }

            animal.setAnimalType(optionalAnimalType.get());

            return animalRepository.save(animal);

        } catch (ParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Parse exception", e);
        }

    }
}