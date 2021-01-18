package com.github.vjiki.wildsql.service.impl;


import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.dto.AreaDTO;
import com.github.vjiki.wildsql.exception.MyResourceNotFoundException;
import com.github.vjiki.wildsql.model.Area;
import com.github.vjiki.wildsql.model.repositories.AreaRepository;
import com.github.vjiki.wildsql.service.common.ISingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AreaService implements ISingleService<Area, AreaDTO> {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private DtoConverter dtoConverter;

    @Override
    @Transactional
    public Area getById(Long id) {
        return areaRepository.getOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<Area> optionalArea = areaRepository.findById(id);

        if (optionalArea.isEmpty()) {
            final String message = Area.class + " id " + id.toString();
            throw new EntityNotFoundException(message);
        }

        areaRepository.delete(optionalArea.get());
    }

    @Transactional
    @Override
    public Area update(AreaDTO areaDTO) {

        try {
            Area area = dtoConverter.convertToAreaEntity(areaDTO);

            Area existingArea = areaRepository.getOne(area.getId());

            Area uniqueNameArea = areaRepository.findByName(area.getName());
            if (uniqueNameArea != null) {
                final String message = Area.class + ": Area with name "
                        + "'" + area.getName() + "'"
                        + " already exists with id: "
                        + "'" + uniqueNameArea.getId() + "'";
                throw new DataIntegrityViolationException(message);
            }

            existingArea.setAreaSquare(area.getAreaSquare());
            existingArea.setCode(area.getCode());
            existingArea.setName(area.getName());
            existingArea.setPersonName(area.getPersonName());
            existingArea.setPersonPhoneNumber(area.getPersonPhoneNumber());

            return areaRepository.save(existingArea);

        } catch (ParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Parse exception", e);
        }
    }

    @Override
    @Transactional
    public List<Area> getList(Pageable pageable) {
        Page<Area> areas = areaRepository.findAll(pageable);

        if (pageable.getPageNumber() > areas.getTotalPages()) {
            int pageNumber = pageable.getPageNumber();
            int totalNumber = areas.getTotalPages();
            final String message = "Page not found: "  + pageNumber + " > " + totalNumber;
            throw new MyResourceNotFoundException(message);
        }

        if (areas.isEmpty())
        {
            final String message = "Page is empty";
            throw new EntityNotFoundException(message);
        }

        return areas.getContent();
    }


    @Transactional
    @Override
    public Area create(AreaDTO areaDTO) {
        try {
            Area area = dtoConverter.convertToAreaEntity(areaDTO);

            Area uniqueNameArea = areaRepository.findByName(area.getName());
            if (uniqueNameArea != null) {
                final String message = Area.class + ": Area with name "
                        + "'" + area.getName() + "'"
                        + " already exists with id: "
                        + "'" + uniqueNameArea.getId() + "'";
                throw new DataIntegrityViolationException(message);
            }

            return areaRepository.save(area);

        } catch (ParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Parse exception", e);
        }
    }

}