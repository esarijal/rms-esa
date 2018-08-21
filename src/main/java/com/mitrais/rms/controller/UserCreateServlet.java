package com.mitrais.rms.controller;

import com.mitrais.rms.model.User;
import com.mitrais.rms.service.UserService;
import com.mitrais.rms.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/createUser")
public class UserCreateServlet extends AbstractController {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("newUser", true);
        request.setAttribute("action", "/createUser");

        request.getRequestDispatcher("/WEB-INF/jsp/userForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean succeed = userService.addUser(new User(userId, firstName, lastName, LocalDate.parse(dob
                , formatter),
                email));

        if(!succeed){
            request.getSession().setAttribute("message", "Fail create user");
        }
        request.getSession().setAttribute("message", "Succeed create user");
        response.sendRedirect("/users");
    }


}
