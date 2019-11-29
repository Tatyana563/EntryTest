package com.repository.postgre;

import com.repository.DAOFactory;
import com.repository.TrackDAO;

/**
 * Created by User on 018 18.11.19.
 */
public class PostgreDAOFactory extends DAOFactory {
    @Override
    public TrackDAO getTrackDAO() {
        return new PostgreTrackDAO();
    }

    @Override
    public DriverDAO getDriverDAO() {
        return new PosgreDriverDAO();
    }


}
