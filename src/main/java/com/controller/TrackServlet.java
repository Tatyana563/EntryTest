package com.controller;

import com.domain.Track;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.CRUDService;
import com.service.impl.TrackService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/track")//localhost:9999/entrytest/track
public class TrackServlet extends HttpServlet {

    public static final TrackService SERVICE = new TrackService();

    //One to many
    // http://localhost:9999/entrytest/track?model_year=2015
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //https://www.baeldung.com/jackson-object-mapper-tutorial
        //example of serializing a Java Object into JSON using the
// writeValue method of ObjectMapper class:
        String param = req.getParameter("model_year");

        int modelYear = Integer.parseInt(param);

        List<Track> tracks = SERVICE.findAllByYear(modelYear);

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(tracks);//list entities(tracks)->json object

        PrintWriter writer = resp.getWriter();

        resp.setHeader("Content-Type", "application/json");

        writer.println(jsonString);//generate HTML page with the json
    }
//Send params for creating Track object in url without jackson
    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int modelYear = Integer.parseInt(req.getParameter("modelYear"));
        String model = req.getParameter("model");

        Track track = new Track(modelYear, model);

        SERVICE.save(track);
    }*/

    //Send json for creating Track object
    //The simple readValue API of the ObjectMapper is a good entry point.
    // We can use it to parse or deserialize JSON content into a Java object.
    //{
    //"modelYear": 2019,
    //	"model": "VAZ"
    //}
    //One to many
    //int driverId = resultSet.getInt("driver_id");
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
        ObjectMapper mapper = new ObjectMapper();//improve

        try (BufferedReader reader = req.getReader()) {

            Track track = mapper.readValue(reader, Track.class);

            SERVICE.save(track);
        }
    }

    //http://localhost:9999/entrytest/track?track_id=23, if id is available
  /*  @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int trackId = Integer.parseInt(req.getParameter("track_id"));

        SERVICE.deleteById(trackId);
    }*/
//http://localhost:9999/entrytest/track?truck_modelYear=1990&truck_model=VAZ
    //without json using url parameters, without jackson library
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int year = Integer.parseInt(req.getParameter("truck_modelYear"));
        String model = req.getParameter("truck_model");

        SERVICE.deleteByYearAndModel(year, model);
    }
//{
//  "id":19,
//	"modelYear": 2019,
//	"model": "VAZ"
//}
    //   @Override
    //  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* ObjectMapper mapper = new ObjectMapper(); //before improve

        try (BufferedReader reader = req.getReader()) {

            Track track = mapper.readValue(reader, Track.class);

            SERVICE.update(track);
        }*/

    //     final Track track = getTrackFromRequest(req);

    //     SERVICE.update(track);

    //  }

    private Track getTrackFromRequest(HttpServletRequest req) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try (BufferedReader reader = req.getReader()) {

            return mapper.readValue(reader, Track.class);
        }
    }


    //  http://localhost:9999/entrytest/track?truck_modelYear=2015&truck_model=Fiat
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int year = Integer.parseInt(req.getParameter("truck_modelYear"));
        String model = req.getParameter("truck_model");
        SERVICE.updateModelByModelYear(year, model);
    }
}
//http://localhost:9999/entrytest/track?model_year=2019
// talented api test
//https://jsonlint.com/
//http://tutorials.jenkov.com/java-servlets/web-xml.html
//{
//	"modelYear": 2011,
//	"model": "VAZ"
//}