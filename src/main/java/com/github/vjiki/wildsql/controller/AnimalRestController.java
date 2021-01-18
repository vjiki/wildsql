package com.github.vjiki.wildsql.controller;

import com.github.vjiki.wildsql.dto.AnimalCreateDTO;
import com.github.vjiki.wildsql.dto.AnimalDTO;
import com.github.vjiki.wildsql.dto.AnimalDTORequest;
import com.github.vjiki.wildsql.dto.AnimalUpdateDTO;
import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.service.common.ISingleService;
import org.modelmapper.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/animals")
public class AnimalRestController {

    //Note :
    //@Transaction annotation will work for only public methods of the class Annotated by @Service annotation.
    //It is necessary that the Service class StudentServiceImple is Autowired, else @Transactional anotation will not work.
    @Autowired
    private ISingleService animalService;

    @Autowired
    private DtoConverter dtoConverter;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(dtoConverter.convertToAnimalDTO((Animal) animalService.getById(id)), HttpStatus.OK);
        } catch (MappingException ex) {
            final String message = "Skipping MappingException: " + Animal.class + " id " + id.toString();
            throw new EntityNotFoundException(message);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        animalService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AnimalDTO> update(@PathVariable("id") Long id, @RequestBody AnimalUpdateDTO animalUpdateDTO) throws ParseException {
        AnimalDTO animalDTO = dtoConverter.convertToAnimalDTO(animalUpdateDTO);
        animalDTO.setId(id);
        return new ResponseEntity<>(dtoConverter.convertToAnimalDTO((Animal) animalService.update(animalDTO)), HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<AnimalDTO>> getAll(Pageable pageable) {

        List <AnimalDTO> animalsDTO = (List<AnimalDTO>) animalService
                .getList(pageable)
                .stream()
                .map(obj -> dtoConverter.convertToAnimalDTO((Animal) obj))
                .collect(Collectors.toList());


        Page<AnimalDTO> animalsDTOPage= new PageImpl<>(animalsDTO, pageable, animalsDTO.size());

        return new ResponseEntity<>(animalsDTOPage, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<AnimalDTO> create(  @RequestBody AnimalCreateDTO animalCreateDTO) throws ParseException {
        AnimalDTO animalDTO = dtoConverter.convertToAnimalDTO(animalCreateDTO);
        Animal animalCreated = (Animal) animalService.create(animalDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(animalCreated.getId()).toUri());

        return new ResponseEntity<>(dtoConverter.convertToAnimalDTO(animalCreated), headers, HttpStatus.CREATED);
    }

    //@TODO: not working
    @PostMapping("createbyname")
    @ResponseBody
    public ResponseEntity<AnimalDTO> createByName(@Validated(AnimalDTORequest.class)  @RequestBody AnimalDTORequest animalRequest) throws ParseException {
        AnimalDTO animalDTO = dtoConverter.convertToAnimalDTO(animalRequest);
        Animal animalCreated = (Animal) animalService.create(animalDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(animalCreated.getId()).toUri());

        return new ResponseEntity<>(dtoConverter.convertToAnimalDTO(animalCreated), headers, HttpStatus.CREATED);
    }
}
