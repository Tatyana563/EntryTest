package com.controller;

import com.domain.Driver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.DriverService;
import com.service.impl.DriverServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
// find drivers who drive more then n-number lorries
@WebServlet(urlPatterns = "/driver/details", name="DriverServlet2")
public class DriverServletDetails extends HttpServlet {
    public static final DriverService<Driver> DRIVER_SERVICE = new DriverServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String reqParameter = req.getParameter("number");
       Integer number = Integer.valueOf(reqParameter);
        List<Driver> drivers = DRIVER_SERVICE.findAllByNumberOfTrucks(number);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(drivers);//list entities(drivers)->json object

        PrintWriter writer = resp.getWriter();

        resp.setHeader("Content-Type", "application/json");

        writer.println(jsonString);//generate HTML page with the json
    }
}
