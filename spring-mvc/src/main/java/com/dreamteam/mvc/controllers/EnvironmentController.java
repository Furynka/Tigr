package com.dreamteam.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jan.novak
 */
@Controller
@RequestMapping("/environments")
public class EnvironmentController {

	private static final Logger LOG = LoggerFactory.getLogger(EnvironmentController.class);


	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "environment/environment";
	}

}

