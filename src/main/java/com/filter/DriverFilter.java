package com.filter;

import com.domain.Driver;
import com.domain.Track;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebFilter(servletNames = {"DriverServlet","DriverServlet2"})
public class DriverFilter implements Filter {
    public static final int AGE = 18;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Create filter for drivers");
    }

    @Override
    public void destroy() {
        System.out.println("Delete filter for drivers");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (Objects.equals(servletRequest.getContentType(), "application/json")) {
            ObjectMapper mapper = new ObjectMapper();
            BufferedReader reader = servletRequest.getReader();
            Driver driver = mapper.readValue(reader, Driver.class);
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(true);
            session.setAttribute("worker", driver);
            if (driver.getAge() > AGE) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                ((HttpServletResponse) servletResponse).setStatus(406);
                final PrintWriter writer = servletResponse.getWriter();
                writer.println("Incorrect driver's age");

            }

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
