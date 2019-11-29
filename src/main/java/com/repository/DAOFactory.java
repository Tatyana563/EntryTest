package com.repository;


import com.repository.mysql.MySQLDAOFactory;
import com.repository.postgre.DriverDAO;
import com.repository.postgre.PostgreDAOFactory;

//single responsibility -  MySQLDAOFactory  is responsible for mysql (only)
//open-close interface if DB is added - add class not modify existing code (MySQLDAOFactory, MySQLTrackDAO)
//Dependency Inversion Principle (presence of abstract class and interface)
public abstract class DAOFactory {
    public static final int POSTGRE = 1;
    public static final int MYSQL = 2;

    public abstract TrackDAO getTrackDAO();
    public abstract DriverDAO getDriverDAO();

    public static DAOFactory getDAOFactory(int factoryId) {
        switch (factoryId) {
            case 1:
                return new PostgreDAOFactory();
            case 2:
                return new MySQLDAOFactory();
            default:
                return new PostgreDAOFactory();
        }
    }
}
