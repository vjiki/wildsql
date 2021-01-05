package com.github.vjiki.wildsql.controllers;

import com.github.vjiki.wildsql.models.TerritoryArea;
import com.github.vjiki.wildsql.repo.TerritoryAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TerritoryAreaController {

    @Autowired
    private TerritoryAreaRepository territoryAreaRepository;

    @PostMapping("/areas")
    public ResponseEntity<?> create(@RequestBody TerritoryArea area) {
        territoryAreaRepository.save(area);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/areas")
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {

        try {
            List<TerritoryArea> areas = new ArrayList<TerritoryArea>();
            Pageable paging = PageRequest.of(page, size);

            Page<TerritoryArea> pageAreas;

            pageAreas = territoryAreaRepository.findAll(paging);

            if (pageAreas.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            areas = pageAreas.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("areas", areas);
            response.put("currentPage", pageAreas.getNumber());
            response.put("totalItems", pageAreas.getTotalElements());
            response.put("totalPages", pageAreas.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/areas/{id}")
    public ResponseEntity<TerritoryArea> read(@PathVariable(name = "id") long id) {
        final TerritoryArea area = territoryAreaRepository.findById(id);

        return area != null
                ? new ResponseEntity<>(area, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/areas/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody TerritoryArea newArea) {
        if(!territoryAreaRepository.existsById(id)){
            newArea.setId(id);
        }

        territoryAreaRepository.save(newArea);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/areas/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        if(!territoryAreaRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        territoryAreaRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
