package com.github.vjiki.wildsql.service.impl;


import com.github.vjiki.wildsql.controller.DtoConverter;
import com.github.vjiki.wildsql.dto.AnimalTypeDto;
import com.github.vjiki.wildsql.dto.AreaDto;
import com.github.vjiki.wildsql.exception.MyException;
import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.model.AnimalType;
import com.github.vjiki.wildsql.model.Area;
import com.github.vjiki.wildsql.model.repositories.AnimalTypeRepository;
import com.github.vjiki.wildsql.model.repositories.AreaRepository;
import com.github.vjiki.wildsql.service.common.ASingleService;
import com.github.vjiki.wildsql.service.common.ISingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
public class AreaService extends ASingleService<Area, AreaDto, AreaRepository>  implements ISingleService<Area, AreaDto> {

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

            //@TODO check existings
            existingArea.setAreaSquare(area.getAreaSquare());
            existingArea.setCode(area.getCode());
            existingArea.setName(area.getName());
            existingArea.setPersonName(area.getPersonName());
            existingArea.setPersonPhoneNumber(area.getPersonPhoneNumber());

            return repository.save(existingArea);
    }

    @Transactional(readOnly = true)
    public Page<AreaDto> getPageWithStat(Pageable pageable) {

        List <AreaDto> areasDTO = dtoConverter.simpleConvert(getList(pageable), AreaDto.class);

        for (AreaDto areaDto : areasDTO) {
            Area area = getById(areaDto.getId());
            if (area.getAnimals() != null) {

                Map<String, Integer> animalNumbers = new HashMap<>();

                for (Animal animal : area.getAnimals()) {
                    String animalTypeName = animal.getAnimalType().getName();
                    animalNumbers.merge(animalTypeName, 1, Integer::sum);
                }
                areaDto.setAnimalNumbers(animalNumbers);
            }
        }

        return new PageImpl<>(areasDTO, pageable, areasDTO.size());
    }

}