package com.github.vjiki.wildsql.controller.impl;

import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.controller.common.AbstractController;
import com.github.vjiki.wildsql.controller.common.InterfaceController;
import com.github.vjiki.wildsql.dto.AreaDto;
import com.github.vjiki.wildsql.model.Area;
import com.github.vjiki.wildsql.service.impl.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/areas")
public class AreaController extends AbstractController<AreaDto, Area, AreaService> implements InterfaceController<AreaDto> {

    @Autowired
    private DtoConverter dtoConverter;

    public AreaController(AreaService service) {
        super(service);
    }

    @Override
    protected Class<AreaDto> getMClass() {
        return AreaDto.class;
    }

    @Override
    public ResponseEntity<Page<AreaDto>> getAll(Pageable pageable) {

        return new ResponseEntity<>(service.getAreasWithNumberOfAnimalsPerAnimalType(pageable), HttpStatus.OK);
    }
}