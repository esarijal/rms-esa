package com.mitrais.rms.controller;

import com.mitrais.rms.service.UserService;
import com.mitrais.rms.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteUser")
public class UserDeleteServlet extends HttpServlet {

    UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userid = request.getParameter("userid");
        boolean succeed = userService.deleteUser(userid);
        if(!succeed){
            request.getSession().setAttribute("message", "Fail deleting message");
        }
        request.getSession().setAttribute("message", "Succeed deleting message");
        response.sendRedirect("/users");
    }
}
