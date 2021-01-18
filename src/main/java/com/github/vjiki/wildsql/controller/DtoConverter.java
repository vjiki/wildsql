package com.github.vjiki.wildsql.controller;

import com.github.vjiki.wildsql.dto.*;
import com.github.vjiki.wildsql.model.Animal;
import com.github.vjiki.wildsql.model.AnimalType;
import com.github.vjiki.wildsql.model.Area;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class DtoConverter {
    @Autowired
    private ModelMapper modelMapper;

    public AnimalDTO convertToAnimalDTO(AnimalDTORequest animalRequest) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
        return modelMapper.map(animalRequest, AnimalDTO.class);
    }

    public AnimalDTO convertToAnimalDTO(AnimalCreateDTO animalCreateDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
        return modelMapper.map(animalCreateDTO, AnimalDTO.class);
    }

    public AnimalDTO convertToAnimalDTO(AnimalUpdateDTO animalUpdateDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
        return modelMapper.map(animalUpdateDTO, AnimalDTO.class);
    }

    public AnimalDTOResponse convertToAnimalDTOResponse(Animal animal) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
        return modelMapper.map(animal, AnimalDTOResponse.class);
    }

    public Animal convertToAnimalEntity(AnimalDTO animalDTO) throws ParseException {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
        return modelMapper.map(animalDTO, Animal.class);
    }

    public AnimalDTO convertToAnimalDTO(Animal animal) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);

        AnimalDTO animalDTO = modelMapper.map(animal, AnimalDTO.class);

        if (animal.getArea() != null) {
            animalDTO.setAreaId(animal.getArea().getId());
            animalDTO.setAreaName(animal.getArea().getName());
        }

        if (animal.getAnimalType() != null) {
            animalDTO.setAnimalTypeId(animal.getAnimalType().getId());
            animalDTO.setAnimalTypeName(animal.getAnimalType().getName());
        }

        return animalDTO;
    }

    public AnimalType convertToAnimalTypeEntity(AnimalTypeDTO animalTypeDTO) throws ParseException {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
        return modelMapper.map(animalTypeDTO, AnimalType.class);
    }

    public AnimalTypeDTO convertToAnimalTypeDTO(AnimalType animalType) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);

        return modelMapper.map(animalType, AnimalTypeDTO.class);
    }

    public AnimalTypeDTO convertToAnimalTypeDTO(AnimalTypeCreateDTO animalTypeCreateDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);

        return modelMapper.map(animalTypeCreateDTO, AnimalTypeDTO.class);
    }

    public Area convertToAreaEntity(AreaDTO areaDTO) throws ParseException {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
        Area area = modelMapper.map(areaDTO, Area.class);
        area.setName(areaDTO.getName());
        return area;
    }

    public AreaDTO convertToAreaDTO(Area area) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
        return modelMapper.map(area, AreaDTO.class);
    }

    public AreaDTO convertToAreaDTO(AreaCreateDTO areaCreateDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
        return modelMapper.map(areaCreateDTO, AreaDTO.class);
    }

    public AreaStatDTO convertToAreaStatDTO(Area area) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
        AreaStatDTO areaStatDTO = modelMapper.map(area, AreaStatDTO.class);

        if (area.getAnimals() != null) {

            Map<String, Integer> animalNumbers = new HashMap<>();

            for (Animal animal : area.getAnimals()) {
                String animalTypeName = animal.getAnimalType().getName();
                animalNumbers.merge(animalTypeName, 1, Integer::sum);
            }
            areaStatDTO.setAnimalNumbers(animalNumbers);
        }

        return areaStatDTO;
    }
}
