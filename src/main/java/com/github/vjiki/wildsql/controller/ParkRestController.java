package com.github.vjiki.wildsql.controller;

import com.github.vjiki.wildsql.dto.AnimalTypeDTO;
import com.github.vjiki.wildsql.dto.AreaStatDTO;
import com.github.vjiki.wildsql.model.Area;
import com.github.vjiki.wildsql.service.common.ISingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/park")
public class ParkRestController {

    @Autowired
    private ISingleService animalTypeService;

    @Autowired
    private ISingleService areaService;

    @Autowired
    private DtoConverter dtoConverter;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<AreaStatDTO>> getAll(Pageable pageable) {

        List<AreaStatDTO> areaStatDTO = (List<AreaStatDTO>) areaService
                .getList(pageable)
                .stream()
                .map(obj -> dtoConverter.convertToAreaStatDTO((Area) obj))
                .collect(Collectors.toList());

        Page<AreaStatDTO> areasStatDTOPage= new PageImpl<>(areaStatDTO, pageable, areaStatDTO.size());

        return new ResponseEntity<>(areasStatDTOPage, HttpStatus.OK);
    }

}