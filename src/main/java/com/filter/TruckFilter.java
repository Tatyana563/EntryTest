package com.filter;


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



//@WebFilter(urlPatterns = {"/track"})//entrytest/entrytest/track
@WebFilter(servletNames = {"CarServlet"})
public class TruckFilter implements Filter {

    public static final int MODEL_YEAR = 1990;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

//update post
// for json (not from browser line)

        if (Objects.equals(servletRequest.getContentType(), "application/json")) {
            ObjectMapper mapper = new ObjectMapper();//improve
            BufferedReader reader = servletRequest.getReader();

            Track track = mapper.readValue(reader, Track.class);

            HttpSession session = ((HttpServletRequest) servletRequest).getSession(true );

            session.setAttribute("lorry", track);

            if (track.getModelYear() > MODEL_YEAR) {

                filterChain.doFilter(servletRequest, servletResponse);
            } else {

                ((HttpServletResponse) servletResponse).setStatus(406);

                final PrintWriter writer = servletResponse.getWriter();

                writer.println("Incorrect model year");
            }
// no json
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Create filter for trucks");
    }

    @Override
    public void destroy() {
        System.out.println("Delete filter for trucks");
    }
}
