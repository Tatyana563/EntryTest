package com.controller;

import com.domain.Track;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.impl.TrackService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
//http://tutorials.jenkov.com/java-servlets/web-xml.html
//https://www.baeldung.com/jackson-object-mapper-tutorial
@WebServlet(urlPatterns = "/track", name = "CarServlet")
public class TrackServlet extends HttpServlet {

    public static final TrackService SERVICE = new TrackService();


    // http://localhost:9999/entrytest/track?model_year=2018
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param = req.getParameter("model_year");

        int modelYear = Integer.parseInt(param);

        List<Track> tracks = SERVICE.findAllByYear(modelYear);

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(tracks);//list entities(tracks)->json object

        PrintWriter writer = resp.getWriter();

        resp.setHeader("Content-Type", "application/json");

        writer.println(jsonString);//generate HTML page with the json
    }


   /* {
        "modelYear": 2015,
    	"model": "Mercedes",
      "driver": {
        "id":1
      }
      }
    }*/
   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Track track = (Track) session.getAttribute("lorry");

        SERVICE.save(track);

        session.removeAttribute("lorry");

        final Cookie cookie = new Cookie("Tanya", "Hello");

        resp.addCookie(cookie);

    }

//http://localhost:9999/entrytest/track?truck_modelYear=1990&truck_model=VAZ
    //without json using url parameters, without jackson library
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int year = Integer.parseInt(req.getParameter("truck_modelYear"));
        String model = req.getParameter("truck_model");

        SERVICE.deleteByYearAndModel(year, model);
    }
/*    {
  "id":19,
	"modelYear": 2019,
	"model": "VAZ"
}*/
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Track track = (Track) session.getAttribute("lorry");

        SERVICE.update(track);

        session.removeAttribute("lorry");

        /*final String value = Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals("Tanya"))
                .findFirst()
                .get().getValue();*/

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("1")) {
                System.out.println(cookie.getValue());
                cookie.setMaxAge(0);
            }
        }
    }

    private Track getTrackFromRequest(HttpServletRequest req) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try (BufferedReader reader = req.getReader()) {

            return mapper.readValue(reader, Track.class);
        }
    }
}
