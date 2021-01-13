package com.github.vjiki.wildsql.domain.animal.controllers;

import com.github.vjiki.wildsql.domain.animal.models.AnimalType;
import com.github.vjiki.wildsql.domain.animal.models.repositories.AnimalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/animaltypes")
public class AnimalTypeRestController {

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @PostMapping
    public ResponseEntity<AnimalType> create(@RequestBody AnimalType animalType) {

        System.out.println(animalType.getName() + " " + animalType.getAnimalClass() + " " + animalType.getGroupOfPopulation());

        AnimalType savedAnimalType = animalTypeRepository.save(animalType);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAnimalType.getId()).toUri();

        return ResponseEntity.created(location).body(savedAnimalType);
    }

    @GetMapping
    public ResponseEntity<Page<AnimalType>> getAll(Pageable pageable) {

        try {
            Page<AnimalType> pageAnimalTypes;

            pageAnimalTypes = animalTypeRepository.findAll(pageable);

            if (pageAnimalTypes.isEmpty())
            {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(pageAnimalTypes);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalType> getById(@PathVariable(name = "id") long id) {
        Optional<AnimalType> optionalAnimalType = animalTypeRepository.findById(id);
        return optionalAnimalType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody AnimalType newAnimalType) {

        if(animalTypeRepository.existsById(id)) {
            newAnimalType.setId(id);
        }

        animalTypeRepository.save(newAnimalType);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id")  long id) {
        Optional<AnimalType> optionalAnimalType = animalTypeRepository.findById(id);
        if (optionalAnimalType.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        animalTypeRepository.delete(optionalAnimalType.get());

        return ResponseEntity.ok().build();
    }

}