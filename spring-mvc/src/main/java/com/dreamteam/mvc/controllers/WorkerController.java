package com.dreamteam.mvc.controllers;

import com.dreamteam.dto.WorkerDTO;
import com.dreamteam.dto.WorkerIdPasswordDTO;
import com.dreamteam.facade.WorkerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by khudiakov on 15.12.2016.
 */
@Controller
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerFacade workerFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String workers(Model model, HttpServletRequest request) {
        model.addAttribute("workers", workerFacade.getAllWorkers());
        return "worker/workers";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public View login(Model model, HttpServletResponse response,
                     @RequestParam(value="email") String email,
                     @RequestParam(value="password") String password) {
        WorkerDTO worker = workerFacade.findWorkerByEmail(email);
        WorkerIdPasswordDTO workerIdPasswordDTO = new WorkerIdPasswordDTO();
        workerIdPasswordDTO.setId(worker.getId());
        workerIdPasswordDTO.setPassword(password);
        if (workerFacade.authenticate(workerIdPasswordDTO)) {
            response.addCookie(new Cookie("worker", worker.getId().toString()));
        }
        return new RedirectView("/pa165/workers/");
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public View logout(Model model, HttpServletResponse response) {
        response.addCookie(new Cookie("worker", ""));
        return new RedirectView("/pa165/workers/");
    }
}
