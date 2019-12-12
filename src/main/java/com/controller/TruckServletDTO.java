package com.controller;

import com.domain.Track;
import com.dto.TruckDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.impl.TrackService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
//http://localhost:9999/entrytest//truck/dto?model_year=2013
@WebServlet(urlPatterns = "/truck/dto")
public class TruckServletDTO extends HttpServlet {
    public static final TrackService SERVICE = new TrackService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("model_year");

        int modelYear = Integer.parseInt(param);

        List<TruckDTO> tracks = SERVICE.findOnlyTrucksByYear(modelYear);

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(tracks);//list entities(tracks)->json object

        PrintWriter writer = resp.getWriter();

        resp.setHeader("Content-Type", "application/json");

        writer.println(jsonString);//generate HTML page with the json
    }
}
