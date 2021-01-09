package com.github.vjiki.wildsql.controllers;

import com.github.vjiki.wildsql.models.Animal;
import com.github.vjiki.wildsql.models.Area;
import com.github.vjiki.wildsql.repo.AnimalRepository;
import com.github.vjiki.wildsql.repo.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/animals")
public class AnimalRestController {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @PostMapping
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {
        Optional<Area> optionalArea = areaRepository.findById(animal.getArea().getId());
        if (!optionalArea.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        animal.setArea(optionalArea.get());

        Animal savedAnimal = animalRepository.save(animal);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAnimal.getId()).toUri();

        return ResponseEntity.created(location).body(savedAnimal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> update(@RequestBody Animal animal, @PathVariable long id) {
        Optional<Area> optionalArea = areaRepository.findById(animal.getArea().getId());
        if (!optionalArea.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        if (!optionalAnimal.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        animal.setArea(optionalArea.get());
        animal.setId(optionalAnimal.get().getId());
        animal.setName(optionalAnimal.get().getName());
        animalRepository.save(animal);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Animal> delete(@PathVariable long id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        if (!optionalAnimal.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        animalRepository.delete(optionalAnimal.get());

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<Animal>> getAll(Pageable pageable) {

        try {
            Page<Animal> pageAnimals;

            pageAnimals = animalRepository.findAll(pageable);

            if (pageAnimals.isEmpty())
            {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(pageAnimals);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getById(@PathVariable long id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        if (!optionalAnimal.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalAnimal.get());
    }
}