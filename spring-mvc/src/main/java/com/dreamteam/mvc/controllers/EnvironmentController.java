
package com.dreamteam.mvc.controllers;

import com.dreamteam.dto.EnvironmentDTO;
import com.dreamteam.facade.EnvironmentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("data", new EnvironmentDTO());
        model.addAttribute("continueLink", "/pa165/environments/create-action");
        model.addAttribute("buttonLabelCode", "tigr-message-crud-create");
        return "environment/environment-form";
    }

    @RequestMapping(value = "create-action", method = RequestMethod.POST)
    public void create(@ModelAttribute("data") EnvironmentDTO dto, HttpServletResponse response) throws IOException {
        environmentFacade.createEnvironment(dto);
        response.sendRedirect("/pa165/environments");
    }

    @RequestMapping("delete/{environmentId}")
    public void delete(@PathVariable("environmentId") int environmentId, HttpServletResponse response) throws IOException {
        environmentFacade.deleteEnvironment(environmentId);
        response.sendRedirect("/pa165/environments");
    }
}
