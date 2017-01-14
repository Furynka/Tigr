package com.dreamteam.mvc.controllers;

import com.dreamteam.dto.SpeciesDTO;
import com.dreamteam.facade.AnimalFacade;
import com.dreamteam.facade.SpeciesFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created by Daniil Khudiakov
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private AnimalFacade animalFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
        model.addAttribute("topOfFoodChain", animalFacade.getTopOfFoodChain());
        return "home";
    }
}
