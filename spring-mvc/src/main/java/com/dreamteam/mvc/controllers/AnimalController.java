
package com.dreamteam.mvc.controllers;

import com.dreamteam.facade.AnimalFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            return "animals/animalList";
    }
}
