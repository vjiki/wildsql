package com.github.vjiki.wildsql.controller.impl;

import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.controller.common.AbstractController;
import com.github.vjiki.wildsql.controller.common.InterfaceController;
import com.github.vjiki.wildsql.dto.AnimalDto;
import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.service.impl.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/animals")
public class AnimalController extends AbstractController<AnimalDto, Animal, AnimalService> implements InterfaceController<AnimalDto> {

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