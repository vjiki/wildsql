package com.github.vjiki.wildsql.toberemoved;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

public enum AnimalEnumDTO {;
    private interface Id { @Null Long getId(); }
    private interface Name { @NotBlank String getName(); }

    private interface AreaId { @Positive Long getAreaId(); }
    private interface AreaName { @Positive String getAreaName(); }

    private interface AnimalTypeId { @Positive Long getAnimalTypeId(); }
    private interface AnimalTypeName { @Positive String getAnimalTypeName(); }

    public enum Request{;
        @Value
        public static class Create implements Name, AreaId, AnimalTypeId {
            String name;
            Long areaId;
            Long animalTypeId;
        }
    }

    public enum Response{;
        @Value
        public static class Default implements Id, Name, AreaName, AnimalTypeName {
            Long id;
            String name;
            String areaName;
            String animalTypeName;
        }

        // not to be sent outside the backend server
        @Value
        public static class Private implements Id, Name,  AreaId, AreaName, AnimalTypeId, AnimalTypeName  {
            Long id;
            String name;
            Long areaId;
            String areaName;
            Long animalTypeId;
            String animalTypeName;
        }
    }

    /*
    private static <DTO extends AreaId & AnimalTypeId> Double getMarkup(DTO dto){
        return (dto.getAnimalTypeId() - dto.getAreaId()) / dto.getAreaId());
    }*/
}
