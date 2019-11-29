package com.repository;

import com.domain.Track;

/**
 * Created by User on 018 18.11.19.
 */
public class TestDAO {
    public static void main(String[] args) {
        DAOFactory postgreFactory = DAOFactory.getDAOFactory(DAOFactory.POSTGRE);

        TrackDAO trackDAO = postgreFactory.getTrackDAO();

//        Track bmw2 = new Track(8,2019, "BMW2");

       /* trackDAO.save(bmw2);
      //  trackDAO.deleteById(3); - ok
      //  trackDAO.findAllByYear(2017); - ok
        trackDAO.update(bmw2);*/


    }
}
