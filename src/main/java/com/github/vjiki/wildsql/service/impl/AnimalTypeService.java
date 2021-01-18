package com.github.vjiki.wildsql.service.impl;

import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.dto.AnimalTypeDTO;
import com.github.vjiki.wildsql.exception.MyResourceNotFoundException;
import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.model.AnimalType;
import com.github.vjiki.wildsql.model.repositories.AnimalTypeRepository;
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

@Transactional
@Service
public class AnimalTypeService implements ISingleService<AnimalType, AnimalTypeDTO> {

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @Autowired
    private DtoConverter dtoConverter;

    @Override
    @Transactional(readOnly = true)
    public AnimalType getById(Long id) {
        return animalTypeRepository.getOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<AnimalType> optionalAnimalType = animalTypeRepository.findById(id);

        if (optionalAnimalType.isEmpty()) {
            final String message = Animal.class + " id " + id.toString();
            throw new EntityNotFoundException(message);
        }

        animalTypeRepository.delete(optionalAnimalType.get());
    }

    @Transactional
    @Override
    public AnimalType update(AnimalTypeDTO animalTypeDTO) {

        try {
            AnimalType animalType = dtoConverter.convertToAnimalTypeEntity(animalTypeDTO);

            AnimalType existingAnimalType = animalTypeRepository.getOne(animalType.getId());

            AnimalType uniqueNameAnimalType = animalTypeRepository.findByName(animalType.getName());
            if (uniqueNameAnimalType != null) {
                final String message = AnimalType.class + ": Animal Type with name "
                        + "'" + animalType.getName() + "'"
                        + " already exists with id: "
                        + "'" + uniqueNameAnimalType.getId() + "'";
                throw new DataIntegrityViolationException(message);
            }

            existingAnimalType.setName(animalType.getName());
            existingAnimalType.setAnimalClass(animalType.getAnimalClass());
            existingAnimalType.setGroupOfPopulation(animalType.getGroupOfPopulation());

            return animalTypeRepository.save(existingAnimalType);

        } catch (ParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Parse exception", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnimalType> getList(Pageable pageable) {
        Page<AnimalType> animalTypes = animalTypeRepository.findAll(pageable);

        if (pageable.getPageNumber() > animalTypes.getTotalPages()) {
            int pageNumber = pageable.getPageNumber();
            int totalNumber = animalTypes.getTotalPages();
            final String message = "Page not found: "  + pageNumber + " > " + totalNumber;
            throw new MyResourceNotFoundException(message);
        }

        if (animalTypes.isEmpty())
        {
            final String message = "Page is empty";
            throw new EntityNotFoundException(message);
        }

        return animalTypes.getContent();
    }


    @Transactional
    @Override
    public AnimalType create(AnimalTypeDTO animalTypeDTO) {
        try {
            AnimalType animalType = dtoConverter.convertToAnimalTypeEntity(animalTypeDTO);

            AnimalType uniqueNameAnimalType = animalTypeRepository.findByName(animalType.getName());
            if (uniqueNameAnimalType != null) {
                final String message = AnimalType.class + ": Animal Type with name "
                        + "'" + animalType.getName() + "'"
                        + " already exists with id: "
                        + "'" + uniqueNameAnimalType.getId() + "'";
                throw new DataIntegrityViolationException(message);
            }

            return animalTypeRepository.save(animalType);

        } catch (ParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Parse exception", e);
        }
    }

}