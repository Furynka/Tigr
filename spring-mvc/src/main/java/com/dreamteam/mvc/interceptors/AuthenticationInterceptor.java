package com.dreamteam.mvc.interceptors;

import com.dreamteam.dto.WorkerDTO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by khudiakov on 15.12.2016.
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter  {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        WorkerDTO worker = new WorkerDTO();
        worker.setEmail("test@test.test");
        worker.setId(1L);
        worker.setAdministrator(true);

        for (Cookie cookie:request.getCookies()) {
            if (cookie.getName().equals("worker")) {
                try {
                    Long workerId = Long.parseLong(cookie.getValue());
                } catch (NumberFormatException ignored) {
                    break;
                }
                request.setAttribute("worker", worker);
                break;
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView)
            throws Exception {
        WorkerDTO worker = (WorkerDTO)request.getAttribute("worker");
        if (worker != null) {
            modelAndView.getModelMap().addAttribute("worker", worker);
        }

    }
}
