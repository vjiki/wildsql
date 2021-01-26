package com.github.vjiki.wildsql.controller.common;

import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.dto.AbstractDto;
import com.github.vjiki.wildsql.model.repositories.common.AbstractEntity;
import com.github.vjiki.wildsql.service.common.InterfaceService;
import org.modelmapper.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

public abstract class AbstractController<M extends AbstractDto, E extends AbstractEntity, S extends InterfaceService<E,M>> implements InterfaceController<M> {

    @Autowired
    private DtoConverter dtoConverter;

    protected abstract Class<M> getMClass();

    protected final S service;

    @Autowired
    protected AbstractController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<M> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(dtoConverter.simpleConvert(service.getById(id), getMClass()), HttpStatus.OK);
        } catch (MappingException ex) {
            final String message = "Skipping MappingException: " + ex.getMessage() + ". Raise not found: " + getMClass() + " id " + id.toString();
            throw  new EntityNotFoundException(message);
        }
    }

    @Override
    public ResponseEntity<M> update(@PathVariable("id") Long id, @Valid @RequestBody M dto) throws ParseException {
        dto.setId(id);
        return new ResponseEntity<>(dtoConverter.simpleConvert(service.update(dto), getMClass()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Page<M>> getAll(Pageable pageable) {
        List<M> dtoList = dtoConverter.simpleConvert(service.getList(pageable), getMClass());
        Page<M> animalTypesDTOPage= new PageImpl<>(dtoList, pageable, dtoList.size());
        return new ResponseEntity<>(animalTypesDTOPage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<M> create(@Valid @RequestBody M dto) throws ParseException {
        E eCreated = service.create(dto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(eCreated.getId()).toUri());

        return new ResponseEntity<>(dtoConverter.simpleConvert(eCreated, getMClass()), headers, HttpStatus.CREATED);
    }
}
