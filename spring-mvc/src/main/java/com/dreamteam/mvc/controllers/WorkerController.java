package com.dreamteam.mvc.controllers;

import com.dreamteam.dto.WorkerDTO;
import com.dreamteam.dto.WorkerIdPasswordDTO;
import com.dreamteam.facade.WorkerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by khudiakov on 15.12.2016.
 */
@Controller
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerFacade workerFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView workers(HttpServletRequest request) {
        WorkerDTO worker = (WorkerDTO) request.getAttribute("worker");
        if (worker == null || !worker.getAdministrator()) {
            return new ModelAndView("redirect:/");
        }
        ModelAndView mav = new ModelAndView("/worker/workers");
        mav.addObject("workers", workerFacade.getAllWorkers());
        return mav;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(RedirectAttributes attr, HttpServletResponse response,
                               @RequestParam(value="email") String email,
                               @RequestParam(value="password") String password)  throws IOException {
        if (workerFacade.findWorkerByEmail(email) != null) {
            attr.addFlashAttribute("message", "User with this email already exists");
        } else {
            WorkerDTO workerDTO = new WorkerDTO();
            workerDTO.setEmail(email);
            workerDTO.setAdministrator(false);
            workerFacade.registerWorker(workerDTO, password);
            WorkerDTO worker = workerFacade.findWorkerByEmail(email);

            Cookie cookie = new Cookie("worker", worker.getId().toString());
            cookie.setPath("/pa165/");
            response.addCookie(cookie);
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/changeRole", method = RequestMethod.POST)
    public ResponseEntity changeRole(HttpServletRequest request,
                                     @RequestParam(value="email") String email,
                                     @RequestParam(value="admin") Boolean admin)  throws IOException {
        WorkerDTO worker = (WorkerDTO) request.getAttribute("worker");
        WorkerDTO workerDTO = workerFacade.findWorkerByEmail(email);
        if (worker == null || !worker.getAdministrator() || workerDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        workerDTO.setAdministrator(admin);
        workerFacade.changeRole(workerDTO);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public View login(Model model, HttpServletResponse response,
                     @RequestParam(value="email") String email,
                     @RequestParam(value="password") String password) {
        WorkerDTO worker = workerFacade.findWorkerByEmail(email);
        if (worker != null) {
            WorkerIdPasswordDTO workerIdPasswordDTO = new WorkerIdPasswordDTO();
            workerIdPasswordDTO.setId(worker.getId());
            workerIdPasswordDTO.setPassword(password);
            if (workerFacade.authenticate(workerIdPasswordDTO)) {
                Cookie cookie = new Cookie("worker", worker.getId().toString());
                cookie.setPath("/pa165/");
                response.addCookie(cookie);
            }
        }
        return new RedirectView("/pa165/");
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public View logout(Model model, HttpServletResponse response) {
        Cookie cookie = new Cookie("worker","");
        cookie.setPath("/pa165/");
        response.addCookie(cookie);
        return new RedirectView("/pa165/");
    }
}
