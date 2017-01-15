package com.dreamteam.mvc.interceptors;

import com.dreamteam.dto.WorkerDTO;
import com.dreamteam.facade.WorkerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by khudiakov on 15.12.2016.
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter  {
    @Autowired
    private WorkerFacade workerFacade;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getCookies()==null) {
            return true;
        }
        Long workerId = null;
        for (Cookie cookie:request.getCookies()) {
            if (cookie.getName().equals("worker")) {
                if (!cookie.getValue().isEmpty()) {
                    try {
                        workerId = Long.parseLong(cookie.getValue());
                    } catch (NumberFormatException ignored) {
                        response.addCookie(new Cookie("worker", ""));
                    }
                }
                break;
            }
        }
        if (workerId != null) {
            WorkerDTO worker = workerFacade.findWorkerById(workerId);
            request.setAttribute("worker", worker);
        }
        return true;
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView)
            throws Exception {
        WorkerDTO worker = (WorkerDTO)request.getAttribute("worker");
        if (worker != null && modelAndView != null) {
            modelAndView.getModelMap().addAttribute("worker", worker);
		}

    }
}
