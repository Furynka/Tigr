
package com.dreamteam.mvc.controllers;

import com.dreamteam.facade.EnvironmentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Eva Ambrušová
 */
@Controller
@RequestMapping("/environments")
public class EnvironmentController {
	@Autowired
	private EnvironmentFacade environmentFacade;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("environmentList", environmentFacade.getAllEnvironments());
		return "environment/environment";
	}
}
