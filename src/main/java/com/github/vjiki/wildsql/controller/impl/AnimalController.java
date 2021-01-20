package com.github.vjiki.wildsql.controller.impl;

import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.controller.common.AController;
import com.github.vjiki.wildsql.controller.common.IController;
import com.github.vjiki.wildsql.dto.AnimalDto;
import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.service.impl.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/animals")
public class AnimalController extends AController<AnimalDto, Animal, AnimalService> implements IController<AnimalDto> {

    @Autowired
    private DtoConverter dtoConverter;

    public AnimalController(AnimalService service) {
        super(service);
    }

    @Override
    protected Class<AnimalDto> getMClass() {
        return AnimalDto.class;
    }
}