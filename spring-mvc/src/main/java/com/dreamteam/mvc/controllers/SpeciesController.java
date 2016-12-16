package com.dreamteam.mvc.controllers;

import com.dreamteam.facade.SpeciesFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jan.novak
 */
@Controller
@RequestMapping("/species")
public class SpeciesController {

	private static final Logger LOG = LoggerFactory.getLogger(SpeciesController.class);

	@Autowired
	private SpeciesFacade speciesFacade;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("speciesList", speciesFacade.getAllSpecieses());
		return "species/list";
	}

}
