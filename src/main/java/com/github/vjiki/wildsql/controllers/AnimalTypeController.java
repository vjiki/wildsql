package com.github.vjiki.wildsql.controllers;


import com.github.vjiki.wildsql.models.AnimalClass;
import com.github.vjiki.wildsql.models.AnimalType;
import com.github.vjiki.wildsql.models.GroupOfPopulation;
import com.github.vjiki.wildsql.repo.AnimalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AnimalTypeController {

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @GetMapping("/animaltype/{id}")
    public String animalTypeDetails(@PathVariable(value = "id") long id, Model model) {
        if(!animalTypeRepository.existsById(id)){
            return "redirect:/animaltypes";
        }

        Optional<AnimalType> animalType = animalTypeRepository.findById(id);
        ArrayList<AnimalType> res = new ArrayList<>();
        animalType.ifPresent(res::add);
        model.addAttribute("animalType", res);
        return "animaltype-details";
    }

    @GetMapping("/animaltype/{id}/edit")
    public String animalTypeEdit(@PathVariable(value = "id") long id, Model model) {
        if(!animalTypeRepository.existsById(id)){
            return "redirect:/animaltypes";
        }

        Optional<AnimalType> animalType = animalTypeRepository.findById(id);
        ArrayList<AnimalType> res = new ArrayList<>();
        animalType.ifPresent(res::add);
        model.addAttribute("animalType", res);
        return "animaltype-edit";
    }

    @PostMapping("/animaltype/{id}/edit")
    public String animalTypePostUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String animalClass, @RequestParam String groupOfPopulation, Model model) {
        AnimalType animalType = animalTypeRepository.findById(id).orElseThrow();
        animalType.setName(name);
        animalType.setAnimalClass(AnimalClass.valueOf(animalClass));
        animalType.setGroupOfPopulation(GroupOfPopulation.valueOf(groupOfPopulation));

        animalTypeRepository.save(animalType);

        return "redirect:/animaltypes";
    }

    @PostMapping("/animaltype/{id}/remove")
    public String animalTypePostRemove(@PathVariable(value = "id") long id, Model model) {
        AnimalType animalType = animalTypeRepository.findById(id).orElseThrow();
        animalTypeRepository.delete(animalType);

        return "redirect:/animaltypes";
    }

    @GetMapping("/animaltype/select")
    public String animalTypeSelect(Model model) {
        model.addAttribute("animalType", new AnimalType());
        return "animaltype-select";
    }

    @PostMapping("/animaltype/select")
    public String animalTypePostSelect(AnimalType animalType, Model model) {
        model.addAttribute("animalType", animalType);
        animalTypeRepository.save(animalType);
        return "redirect:/animaltypes";
    }

    @GetMapping("/animaltypes")
    public String animalTypePage(HttpServletRequest request, Model model) {

        int page = 0; //default page number is 0 (yes it is weird)
        int size = 5; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("animaltypes", animalTypeRepository.findAll(PageRequest.of(page, size)));
        return "animaltypes";
    }

}
