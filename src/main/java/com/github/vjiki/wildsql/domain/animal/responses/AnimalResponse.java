package com.github.vjiki.wildsql.domain.animal.responses;

import com.github.vjiki.wildsql.domain.animal.entities.Animal;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AnimalResponse {

    private Page<AnimalResponseEntry> animalResponseEntryPage;

    @Data
    @NoArgsConstructor
    private class AnimalResponseEntry {
        Animal animal;
        String areaName;
        String animalTypeName;

        AnimalResponseEntry (Animal animal, String areaName, String animalTypeName) {
            this.animal = animal;
            this.areaName = areaName;
            this.animalTypeName = animalTypeName;
        }

        public Animal getAnimal() {
            return animal;
        }

        public void setAnimal(Animal animal) {
            this.animal = animal;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getAnimalTypeName() {
            return animalTypeName;
        }

        public void setAnimalTypeName(String animalTypeName) {
            this.animalTypeName = animalTypeName;
        }
    }

    public AnimalResponse(Page<Animal> pageAnimals, Pageable pageable) {
        List<AnimalResponseEntry> animalResponseEntriesList = new ArrayList<>();

        for (Animal animal : pageAnimals.getContent()) {
            AnimalResponseEntry animalResponseEntry = new AnimalResponseEntry(animal, animal.getArea().getAreaName(), animal.getAnimalType().getName());
            animalResponseEntriesList.add(animalResponseEntry);
        }

        this.animalResponseEntryPage = new PageImpl<>(animalResponseEntriesList, pageable, animalResponseEntriesList.size());
    }

    public Page<AnimalResponseEntry> getAnimalResponseEntryPage() {
        return animalResponseEntryPage;
    }

    public void setAnimalResponseEntryPage(Page<AnimalResponseEntry> animalResponseEntryPage) {
        this.animalResponseEntryPage = animalResponseEntryPage;
    }
}