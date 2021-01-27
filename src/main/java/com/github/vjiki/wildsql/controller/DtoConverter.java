package com.github.vjiki.wildsql.controller;

import com.github.vjiki.wildsql.dto.*;
import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.model.AnimalType;
import com.github.vjiki.wildsql.model.Area;
import com.github.vjiki.wildsql.service.common.InterfaceService;
import com.github.vjiki.wildsql.service.impl.AreaService;
import lombok.NoArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
public class DtoConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InterfaceService areaService;

    @Autowired
    private InterfaceService animalTypeService;

    @PostConstruct
    public void setupMapper() {
        modelMapper.typeMap(Animal.class,AnimalDto.class)
                .addMappings(m -> m.skip(AnimalDto::setAreaName))
                .addMappings(m -> m.skip(AnimalDto::setAnimalTypeName))
                .setPostConverter(toDtoConverter());
        modelMapper.typeMap(AnimalDto.class, Animal.class)
                .addMappings(m -> m.skip(Animal::setArea))
                .addMappings(m -> m.skip(Animal::setAnimalType))
                .setPostConverter(toEntityConverter());
        modelMapper.typeMap(Area.class, AreaDto.class)
                .addMappings(m -> m.skip(AreaDto::setAnimalNumbers))
                .setPostConverter(toAreaDtoConverter());
    }

    public <T> T simpleConvert(Object obj, Class<T> clazz) {
        return modelMapper.map(obj, clazz);
    }

    public <T> List<T> simpleConvert(List<?> entitiesList, Class<T> clazz) {
        return entitiesList.stream().map(x -> simpleConvert(x, clazz)).collect(Collectors.toList());
    }

    public Converter<Animal, AnimalDto> toDtoConverter() {
        return context -> {
            Animal source = context.getSource();
            AnimalDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(Animal source, AnimalDto destination) {
        destination.setAreaName(Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getArea().getName());
        destination.setAnimalTypeName(Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAnimalType().getName());
    }

    public Converter<AnimalDto, Animal> toEntityConverter() {
        return context -> {
            AnimalDto source = context.getSource();
            Animal destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(AnimalDto source, Animal destination) {
        try {
            destination.setArea((Area) areaService.getById(source.getAreaId()));
        } catch (NoSuchElementException e) {
            destination.setArea(null);
        }
        try {
            destination.setAnimalType((AnimalType) animalTypeService.getById(source.getAnimalTypeId()));
        } catch (NoSuchElementException e) {
            destination.setAnimalType(null);
        }
    }

    public Converter<Area, AreaDto> toAreaDtoConverter() {
        return context -> {
            Area source = context.getSource();
            AreaDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapSpecificFields(Area source, AreaDto destination) {
        AreaService service = (AreaService) areaService;
        destination.setAnimalNumbers(Objects.isNull(source) || Objects.isNull(source.getId()) ? null : service.getNumberOfAnimalsPerAnimalType(destination));
    }
}
