package com.github.vjiki.wildsql.domain.area.controllers;

import com.github.vjiki.wildsql.domain.area.entities.Area;
import com.github.vjiki.wildsql.domain.area.repositories.AreaRepository;
import com.github.vjiki.wildsql.domain.area.responses.AreaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/areas")
public class AreaRestController {

    @Autowired
    private AreaRepository areaRepository;

    @PostMapping
    public ResponseEntity<Area> create(@RequestBody Area area) {
        Area savedArea = areaRepository.save(area);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedArea.getId()).toUri();

        return ResponseEntity.created(location).body(savedArea);
    }

    @GetMapping
    public ResponseEntity<AreaResponse> getAllWithGroup(Pageable pageable) {

        try {
            Page<Area> pageAreas;

            pageAreas = areaRepository.findAll(pageable);

            if (pageAreas.isEmpty())
            {
                return ResponseEntity.notFound().build();
            }

            AreaResponse areaResponse = new AreaResponse(pageAreas);

            return ResponseEntity.ok(areaResponse);
            //Response.ok().entity(Entity.json(result)).build();

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> read(@PathVariable(name = "id") long id) {

        if (!areaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(areaRepository.findById(id).orElseThrow());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody Area newArea) {

        if(areaRepository.existsById(id)) {
            newArea.setId(id);
        }

        areaRepository.save(newArea);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Area> delete(@PathVariable(name = "id") long id) {
        if(!areaRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        areaRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
