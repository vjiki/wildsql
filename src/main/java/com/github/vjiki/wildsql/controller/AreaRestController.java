package com.github.vjiki.wildsql.controller;

import com.github.vjiki.wildsql.dto.AnimalTypeDTO;
import com.github.vjiki.wildsql.dto.AreaCreateDTO;
import com.github.vjiki.wildsql.dto.AreaDTO;
import com.github.vjiki.wildsql.model.Area;
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
@RequestMapping("/api/areas")
public class AreaRestController {

    @Autowired
    private ISingleService areaService;

    @Autowired
    private DtoConverter dtoConverter;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<AreaDTO> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(dtoConverter.convertToAreaDTO((Area) areaService.getById(id)), HttpStatus.OK);
        } catch (MappingException ex) {
            final String message = "Skipping MappingException: " + Area.class + " id " + id.toString();
            throw  new EntityNotFoundException(message);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AreaDTO> update(@PathVariable("id") Long id, @RequestBody AreaDTO areaDTO) throws ParseException {
        areaDTO.setId(id);
        return new ResponseEntity<>(dtoConverter.convertToAreaDTO((Area) areaService.update(areaDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        areaService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<AreaDTO>> getAll(Pageable pageable) {

        List<AreaDTO> areasDTO = (List<AreaDTO>) areaService
                .getList(pageable)
                .stream()
                .map(obj -> dtoConverter.convertToAreaDTO((Area) obj))
                .collect(Collectors.toList());


        Page<AreaDTO> areasDTOPage= new PageImpl<>(areasDTO, pageable, areasDTO.size());

        return new ResponseEntity<>(areasDTOPage, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<AreaDTO> create( @RequestBody AreaCreateDTO areaCreateDTO) throws ParseException {
        AreaDTO areaDTO = dtoConverter.convertToAreaDTO(areaCreateDTO);
        Area areaCreated = (Area) areaService.create(areaDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(areaCreated.getId()).toUri());

        return new ResponseEntity<>(dtoConverter.convertToAreaDTO(areaCreated), headers, HttpStatus.CREATED);
    }

    @PostMapping("default")
    @ResponseBody
    public ResponseEntity<AreaDTO> create() throws ParseException {

        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId(0L);
        areaDTO.setName("DEFAULT");
        areaDTO.setAreaSquare(0.0);
        areaDTO.setCode("000");
        areaDTO.setPersonName("UNKNOWN");
        areaDTO.setPersonPhoneNumber("UNKNOWN");

        Area areaCreated = (Area) areaService.create(areaDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(areaCreated.getId()).toUri());

        return new ResponseEntity<>(dtoConverter.convertToAreaDTO(areaCreated), headers, HttpStatus.CREATED);
    }


}