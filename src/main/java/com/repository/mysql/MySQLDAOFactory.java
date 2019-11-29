package com.repository.mysql;

import com.repository.DAOFactory;
import com.repository.TrackDAO;
import com.repository.postgre.DriverDAO;


public class MySQLDAOFactory extends DAOFactory {
    @Override
    public TrackDAO getTrackDAO() {

        return new MySQLTrackDAO();
    }

    @Override
    public DriverDAO getDriverDAO() {
        return null;
    }
}
