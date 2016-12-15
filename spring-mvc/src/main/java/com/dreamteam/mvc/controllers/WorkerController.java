package com.dreamteam.mvc.controllers;

import com.dreamteam.dto.WorkerDTO;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khudiakov on 15.12.2016.
 */
@Controller
@RequestMapping("/workers")
public class WorkerController {
    private int count;
//    @Autowired
//    private WorkerFacade workerFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String workers(Model model, HttpServletRequest request) {
//        model.addAttribute("users", userFacade.getAllUsers());
        count = 0;
        List<WorkerDTO> workers = new ArrayList<>();
        for (int i=0; i<10; i++) {
            workers.add(createWorkerMockUp());
        }
        model.addAttribute("workers", workers);
        return "worker/worker";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public View login(Model model, HttpServletResponse response,
                     @RequestParam(value="email") String email,
                     @RequestParam(value="password") String password) {
        if (email.equals("test") && password.equals("test")) {
            response.addCookie(new Cookie("worker", "1"));
        }
        return new RedirectView("/pa165/workers/");
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public View logout(Model model, HttpServletResponse response) {
        response.addCookie(new Cookie("worker", ""));
        return new RedirectView("/pa165/workers/");
    }


    private WorkerDTO createWorkerMockUp() {
        WorkerDTO worker = new WorkerDTO();
        worker.setAdministrator(Math.random()>0.8);
        worker.setId((long) ++count);
        worker.setEmail("worker"+count+"@mail.com");
        return worker;
    };
}
