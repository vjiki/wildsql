package com.github.vjiki.wildsql.controller.impl;

import com.github.vjiki.wildsql.constants.AnimalClassEnum;
import com.github.vjiki.wildsql.constants.GroupOfPopulationEnum;
import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.controller.common.AController;
import com.github.vjiki.wildsql.controller.common.IController;
import com.github.vjiki.wildsql.dto.AnimalTypeDto;
import com.github.vjiki.wildsql.model.AnimalType;
import com.github.vjiki.wildsql.service.impl.AnimalTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/animaltypes")
public class AnimalTypeController extends AController<AnimalTypeDto, AnimalType, AnimalTypeService> implements IController<AnimalTypeDto> {

    @Autowired
    private DtoConverter dtoConverter;

    public AnimalTypeController(AnimalTypeService service) {
            super(service);
    }

    @Override
    protected Class<AnimalTypeDto> getMClass() {
        return AnimalTypeDto.class;
    }

    @PostMapping("default")
    @ResponseBody
    public ResponseEntity<AnimalTypeDto> createDefault() throws ParseException {

        AnimalTypeDto animalTypeDTO = new AnimalTypeDto();
        animalTypeDTO.setId(0L);
        animalTypeDTO.setName("UNKNOWN");
        animalTypeDTO.setAnimalClass(AnimalClassEnum.UNKNOWN);
        animalTypeDTO.setGroupOfPopulation(GroupOfPopulationEnum.UNKNOWN);

        return create(animalTypeDTO);
    }


    @GetMapping("groups")
    @ResponseBody
    public ResponseEntity<?> getGroups() {
        Map<String, Object> groups = new HashMap<>();

        for (GroupOfPopulationEnum group : GroupOfPopulationEnum.values()) {
            groups.put(group.toString(), group.getNumberOfAnimals());
        }

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("classes")
    @ResponseBody
    public ResponseEntity<?> getClasses() {
        List<String> classes = new ArrayList<>();

        for (AnimalClassEnum e : AnimalClassEnum.values()) {
            classes.add(e.toString());
        }

        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

}