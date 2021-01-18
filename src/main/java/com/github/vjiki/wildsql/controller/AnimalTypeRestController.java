package com.github.vjiki.wildsql.controller;

import com.github.vjiki.wildsql.constants.AnimalClassEnum;
import com.github.vjiki.wildsql.constants.GroupOfPopulationEnum;
import com.github.vjiki.wildsql.dto.AnimalCreateDTO;
import com.github.vjiki.wildsql.dto.AnimalDTO;
import com.github.vjiki.wildsql.dto.AnimalTypeCreateDTO;
import com.github.vjiki.wildsql.dto.AnimalTypeDTO;
import com.github.vjiki.wildsql.model.AnimalType;
import com.github.vjiki.wildsql.service.common.ISingleService;
import org.modelmapper.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/animaltypes")
public class AnimalTypeRestController {

    @Autowired
    private ISingleService animalTypeService;

    @Autowired
    private DtoConverter dtoConverter;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<AnimalTypeDTO> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(dtoConverter.convertToAnimalTypeDTO((AnimalType) animalTypeService.getById(id)), HttpStatus.OK);
        } catch (MappingException ex) {
            final String message = "Skipping MappingException: " + AnimalType.class + " id " + id.toString();
            throw  new EntityNotFoundException(message);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AnimalTypeDTO> update(@PathVariable("id") Long id, @RequestBody AnimalTypeDTO animalTypeDTO) throws ParseException {
        animalTypeDTO.setId(id);
        return new ResponseEntity<>(dtoConverter.convertToAnimalTypeDTO((AnimalType) animalTypeService.update(animalTypeDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        animalTypeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<AnimalTypeDTO>> getAll(Pageable pageable) {

        List <AnimalTypeDTO> animalTypesDTO = (List<AnimalTypeDTO>) animalTypeService
                .getList(pageable)
                .stream()
                .map(obj -> dtoConverter.convertToAnimalTypeDTO((AnimalType) obj))
                .collect(Collectors.toList());

        Page<AnimalTypeDTO> animalTypesDTOPage= new PageImpl<>(animalTypesDTO, pageable, animalTypesDTO.size());

        return new ResponseEntity<>(animalTypesDTOPage, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<AnimalTypeDTO> create( @RequestBody AnimalTypeCreateDTO animalTypeCreateDTO) throws ParseException {
        AnimalTypeDTO animalTypeDTO = dtoConverter.convertToAnimalTypeDTO(animalTypeCreateDTO);

        AnimalType animalTypeCreated = (AnimalType) animalTypeService.create(animalTypeDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(animalTypeCreated.getId()).toUri());

        return new ResponseEntity<>(dtoConverter.convertToAnimalTypeDTO(animalTypeCreated), headers, HttpStatus.CREATED);
    }

    @PostMapping("default")
    @ResponseBody
    public ResponseEntity<AnimalTypeDTO> create() throws ParseException {

        AnimalTypeDTO animalTypeDTO = new AnimalTypeDTO();
        animalTypeDTO.setId(0L);
        animalTypeDTO.setName("UNKNOWN");
        animalTypeDTO.setAnimalClass(AnimalClassEnum.UNKNOWN);
        animalTypeDTO.setGroupOfPopulation(GroupOfPopulationEnum.UNKNOWN);

        AnimalType animalTypeCreated = (AnimalType) animalTypeService.create(animalTypeDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(animalTypeCreated.getId()).toUri());

        return new ResponseEntity<>(dtoConverter.convertToAnimalTypeDTO(animalTypeCreated), headers, HttpStatus.CREATED);
    }

}