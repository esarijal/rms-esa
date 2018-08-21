package com.mitrais.rms.controller;

import com.mitrais.rms.service.LoginService;
import com.mitrais.rms.service.impl.LoginServiceImpl;

import javax.security.auth.login.LoginException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends AbstractController {

    private LoginService loginService = LoginServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = getTemplatePath(req.getServletPath());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        String password = req.getParameter("password");

        try {
            boolean isValidUser = loginService.validateUser(username, password);
            if(isValidUser){
                req.getSession().setAttribute("loggedUserId", username);
                resp.sendRedirect("/users");
            } else {
                throw new LoginException("Not valid user");
            }
        } catch (LoginException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(getTemplatePath("/login")).forward(req, resp);
        }

    }
}
