/*
package com.filter;

import com.domain.Driver;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

//check
//@WebFilter(urlPatterns = {"/driver"})
public class DriverFilter implements Filter {
    public static final int DRIVER_AGE = 18;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (Objects.equals(servletRequest.getContentType(), "application/json")) {
            ObjectMapper mapper = new ObjectMapper();
            try (BufferedReader reader = servletRequest.getReader()) {
                Driver driver = mapper.readValue(reader, Driver.class);
                if (driver.getAge() > DRIVER_AGE) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    final PrintWriter writer = servletResponse.getWriter();
                    writer.println("Incorrect age of the driver");
                }
            }
        }
    }
}
*/
