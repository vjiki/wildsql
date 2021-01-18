package com.github.vjiki.wildsql.service.impl;

import com.github.vjiki.wildsql.dto.AnimalDTO;
import com.github.vjiki.wildsql.dto.AnimalTypeDTO;
import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.service.common.ISingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Transactional
@Component
public class ParkService {

    @Autowired
    private ISingleService<Animal, AnimalDTO> animalService;

    @Autowired
    private ISingleService animalTypeService;

    public void saveAnimalAndAnimalType() {
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setName("John Smith");

        //animalDTO = (AnimalDTO) animalService.create(animalDTO);

        AnimalTypeDTO animalTypeDTO = new AnimalTypeDTO();
        animalTypeDTO.setName("Mr Robot");

        //    animalTypeService.create(animalTypeDTO);

    }
}