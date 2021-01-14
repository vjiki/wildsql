package com.github.vjiki.wildsql.domain.animal.responses;

import com.github.vjiki.wildsql.domain.animal.models.Animal;
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

    public AnimalResponse(Page<Animal> pageAnimals, Pageable pageable) {
        List<AnimalResponseEntry> animalResponseEntriesList = new ArrayList<>();

        for (Animal animal : pageAnimals.getContent()) {
            AnimalResponseEntry animalResponseEntry = new AnimalResponseEntry(animal, animal.getArea().getAreaName(), animal.getAnimalType().getName());
            animalResponseEntriesList.add(animalResponseEntry);
        }

        this.animalResponseEntryPage = new PageImpl<>(animalResponseEntriesList, pageable, animalResponseEntriesList.size());
    }
}