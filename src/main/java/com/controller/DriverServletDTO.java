package com.controller;

import com.domain.Driver;
import com.domain.Experience;
import com.dto.DriverDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.DriverService;
import com.service.impl.DriverServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/driver/dto")
public class DriverServletDTO extends HttpServlet {
    public static final DriverService<Driver> DRIVER_SERVICE = new DriverServiceImpl();

//http://localhost:9999/entrytest/driver/dto?qualification=MEDIUM
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Experience experience = Experience.valueOf(req.getParameter("qualification")/*.toUpperCase*/);

        List<DriverDTO> drivers = DRIVER_SERVICE.findAllOnlyDriversByExperience(experience);

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(drivers);//list entities(drivers)->json object

        PrintWriter writer = resp.getWriter();

        resp.setHeader("Content-Type", "application/json");

        writer.println(jsonString);//generate HTML page with the json
    }

}
