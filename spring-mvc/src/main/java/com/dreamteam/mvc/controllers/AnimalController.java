
package com.dreamteam.mvc.controllers;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.facade.AnimalFacade;
import com.dreamteam.facade.SpeciesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Jiri Oliva
 */
@Controller
@RequestMapping("/animals")
public class AnimalController {
    @Autowired
    private AnimalFacade animalFacade;
    
    @Autowired
    private SpeciesFacade speciesFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
            model.addAttribute("animalList", animalFacade.getAllAnimals());
		return "animal/animal";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("data", new AnimalDTO());
        model.addAttribute("continueLink", "/pa165/animals/create-action");
        model.addAttribute("buttonLabelCode", "tigr-message-crud-create");
		model.addAttribute("speciesList", speciesFacade.getAllSpecieses());
		return "animal/animal-form";
    }
    
    
    @RequestMapping(value = "create-action", method = RequestMethod.POST)
    public void create(@ModelAttribute("data") AnimalDTO animal, HttpServletResponse response) throws IOException {
        animalFacade.createAnimal(animal);
        response.sendRedirect("/pa165/animals");
    }
    
    @RequestMapping("delete/{animalId}")
    public void delete(@PathVariable("animalId") Long animalId, HttpServletResponse response) throws IOException {
        animalFacade.deleteAnimal(animalId);
        response.sendRedirect("/pa165/animals");
    }
    
    @RequestMapping(value = "edit/{animalId}", method = RequestMethod.GET)
    public String edit(@PathVariable("animalId") long animalId, Model model) {
        model.addAttribute("data", animalFacade.findAnimalById(animalId));
        model.addAttribute("continueLink", "/pa165/animals/edit-action");
        model.addAttribute("buttonLabelCode", "tigr-message-crud-update");
        return "animal/animal-form";
    }

    @RequestMapping(value = "edit-action", method = RequestMethod.POST)
    public void edit(@ModelAttribute("data") AnimalDTO animal, HttpServletRequest request, HttpServletResponse response) throws IOException {

        animalFacade.changeAnimalName(animal.getId(), animal.getName());
        animalFacade.changeAnimalDescription(animal.getId(), animal.getDescription());
        animalFacade.changeAnimalCount(animal.getId(), animal.getCount());
        response.sendRedirect("/pa165/animals");
    }
    
}
