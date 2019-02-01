package com.mitrais.rms.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "*")
public class LoginRequiredFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        boolean isStaticResource = ((HttpServletRequest) request).getRequestURI().startsWith("/resources/");
        boolean isLoggedIn = req.getSession().getAttribute("loggedUserId") != null;

        if(isLoggedIn || isStaticResource){
            filterChain.doFilter(request, response);
        } else {
            req.getRequestDispatcher("/login")
                    .forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
