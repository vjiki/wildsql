package com.github.vjiki.wildsql.controllers;

import com.github.vjiki.wildsql.models.AnimalType;
import com.github.vjiki.wildsql.repo.AnimalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

@RestController
@RequestMapping("/api")
public class AnimalTypeRestController {

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @PostMapping("/animaltypes")
    public ResponseEntity<?> create(@RequestBody AnimalType animalType) {
        animalTypeRepository.save(animalType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/animaltypes")
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {

        try {
            List<AnimalType> animalTypes = new ArrayList<AnimalType>();
            Pageable paging = PageRequest.of(page, size);

            Page<AnimalType> pageAnimalTypes;

            pageAnimalTypes = animalTypeRepository.findAll(paging);

            if (pageAnimalTypes.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            animalTypes = pageAnimalTypes.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("animalTypes", animalTypes);
            response.put("currentPage", pageAnimalTypes.getNumber());
            response.put("totalItems", pageAnimalTypes.getTotalElements());
            response.put("totalPages", pageAnimalTypes.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/animaltypes/{id}")
    public ResponseEntity<AnimalType> read(@PathVariable(name = "id") long id) {
        Optional<AnimalType> animalType = animalTypeRepository.findById(id);

        return animalType != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/animaltypes/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody AnimalType newAnimalType) {
        if(!animalTypeRepository.existsById(id)){
            newAnimalType.setId(id);
        }

        animalTypeRepository.save(newAnimalType);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/animaltypes/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        if(!animalTypeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        animalTypeRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}