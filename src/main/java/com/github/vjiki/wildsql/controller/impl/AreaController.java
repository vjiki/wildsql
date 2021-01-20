package com.github.vjiki.wildsql.controller.impl;

import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.controller.common.AController;
import com.github.vjiki.wildsql.controller.common.IController;
import com.github.vjiki.wildsql.dto.AreaDto;
import com.github.vjiki.wildsql.model.Area;
import com.github.vjiki.wildsql.service.impl.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.ParseException;

@RestController
@RequestMapping("/api/areas")
public class AreaController extends AController<AreaDto, Area, AreaService> implements IController<AreaDto> {

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

        return new ResponseEntity<>(service.getPageWithStat(pageable), HttpStatus.OK);
    }

    @PostMapping("default")
    @ResponseBody
    public ResponseEntity<AreaDto> create() throws ParseException {

        AreaDto areaDTO = new AreaDto();
        areaDTO.setId(0L);
        areaDTO.setName("DEFAULT");
        areaDTO.setAreaSquare(0.0);
        areaDTO.setCode("000");
        areaDTO.setPersonName("UNKNOWN");
        areaDTO.setPersonPhoneNumber("UNKNOWN");

        return create(areaDTO);
    }
}