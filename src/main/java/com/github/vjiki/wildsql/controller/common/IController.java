package com.github.vjiki.wildsql.controller.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;


public interface IController<M> {

    /*
    @GetMapping("/")
    @ResponseBody
    List<M> getAll();
     */

    @GetMapping("/{id}")
    @ResponseBody
    ResponseEntity<M> getById(@PathVariable("id") Long id);

    @PutMapping(value = "/{id}")
    @ResponseBody
    ResponseEntity<M> update(@PathVariable("id") Long id, @Valid @RequestBody M dto) throws ParseException;

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id);

    @GetMapping
    @ResponseBody
    ResponseEntity<Page<M>> getAll(Pageable pageable);

    @PostMapping
    @ResponseBody
    ResponseEntity<M> create(@Valid @RequestBody M dto) throws ParseException;
}