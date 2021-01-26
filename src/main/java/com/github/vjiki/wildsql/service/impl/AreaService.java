package com.github.vjiki.wildsql.service.impl;


import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.dto.AreaDto;
import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.model.Area;
import com.github.vjiki.wildsql.model.repositories.impl.AreaRepository;
import com.github.vjiki.wildsql.service.common.AbstractService;
import com.github.vjiki.wildsql.service.common.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@TODO avoid big number of arguments in abstract/interface classes
@Transactional
@Service
public class AreaService extends AbstractService<Area, AreaDto, AreaRepository> implements InterfaceService<Area, AreaDto> {

    @Autowired
    private DtoConverter dtoConverter;

    public AreaService(AreaRepository repository) {
        super(repository);
    }

    @Override
    protected Class<Area> getEClass() {
        return Area.class;
    }

    @Transactional
    public Area update(AreaDto areaDTO) {

            Area area = dtoConverter.simpleConvert(areaDTO, Area.class);

            Area existingArea = preUpdate(area.getId(), area.getName());

            //@TODO check existing
            existingArea.setAreaSquare(area.getAreaSquare());
            existingArea.setCode(area.getCode());
            existingArea.setName(area.getName());
            existingArea.setPersonName(area.getPersonName());
            existingArea.setPersonPhoneNumber(area.getPersonPhoneNumber());

            return repository.save(existingArea);
    }

    // @TODO make it better with Query
    @Transactional(readOnly = true)
    public Map<String, Integer> getNumberOfAnimalsPerAnimalType(AreaDto areaDto) {
        Area area = getById(areaDto.getId());
        if (area.getAnimals() != null) {

            Map<String, Integer> animalNumbers = new HashMap<>();

            for (Animal animal : area.getAnimals()) {
                String animalTypeName = animal.getAnimalType().getName();
                animalNumbers.merge(animalTypeName, 1, Integer::sum);
            }
            return animalNumbers;
        }
        return null;
    }

}