package com.controller;

import com.domain.Driver;
import com.domain.Experience;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.DriverService;
import com.service.impl.DriverServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/driver")//localhost:9999/entrytest/driver?qualification=well-qualified
public class DriverServlet extends HttpServlet {
    public static final DriverService<Driver> DRIVER_SERVICE = new DriverServiceImpl();

    //http://localhost:9999/entrytest/driver
//    +
/*
    {
        "name":"Mark",
            "age":45,
            "experience":"good"//enum
    }

    */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        try (BufferedReader reader = req.getReader()) {
            Driver driver = mapper.readValue(reader, Driver.class);
            DRIVER_SERVICE.save(driver);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Experience experience = Experience.valueOf(req.getParameter("qualification")/*.toUpperCase*/);

        List<Driver> drivers = DRIVER_SERVICE.findAllByExperience(experience);

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(drivers);//list entities(drivers)->json object

        PrintWriter writer = resp.getWriter();

        resp.setHeader("Content-Type", "application/json");

        writer.println(jsonString);//generate HTML page with the json
    }

    //localhost:9999/entrytest/driver?driverId=5
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("driverId"));
        DRIVER_SERVICE.deleteById(id);
    }

    //http://localhost:9999/entrytest/driver?dName=Mark&dExperience=no
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("dName");
        String qualification = req.getParameter("dExperience");
        DRIVER_SERVICE.updateExperienceByName(name, qualification);
    }


}
