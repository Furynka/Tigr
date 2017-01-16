
package com.dreamteam.mvc.controllers;

import com.dreamteam.dto.AnimalDTO;
import com.dreamteam.facade.AnimalFacade;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Jiri Oliva
 */
@Controller
@RequestMapping("/animals")
public class AnimalController {
    @Autowired
    private AnimalFacade animalFacade;

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
        return "animals/animals-form";
    }
    
    @RequestMapping(value = "create-action", method = RequestMethod.POST)
    public void create(@ModelAttribute("data") AnimalDTO animal, HttpServletResponse response) throws IOException {
        animalFacade.createAnimal(animal);
        response.sendRedirect("/pa165/animals");
    }
    
    @RequestMapping("delete/{environmentId}")
    public void delete(@PathVariable("animalId") Long animalId, HttpServletResponse response) throws IOException {
        animalFacade.deleteAnimal(animalId);
        response.sendRedirect("/pa165/animals");
    }
    
}
