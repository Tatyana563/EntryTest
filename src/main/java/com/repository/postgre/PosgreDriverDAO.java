package com.repository.postgre;

import com.domain.Driver;
import com.domain.Track;
import com.repository.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PosgreDriverDAO implements DriverDAO {

    @Override
    public void save(Driver driver) {
        Connection connection = ConnectionFactory.getConnection();
        String query = "INSERT INTO driver (driver_name, driver_age, qualification) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, driver.getName());
            statement.setInt(2, driver.getAge());
            statement.setString(3, driver.getExperience());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Driver driver) {

    }

    @Override
    public void deleteById(int driverId) {
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(
                "DELETE from driver as d WHERE d.driver_id=?"//uppercase
        )) {
            statement.setInt(1, driverId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Driver> findAllByExperience(String experience) {
        List<Driver> driverList = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(
                "SELECT * FROM track AS t " +
                        "INNER JOIN Driver AS d " +
                        "ON t.driver_fk_id = d.driver_id " +
                        "WHERE d.qualification = ?"
        )) {
            statement.setString(1, experience);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("track_id");
                int modelYear = resultSet.getInt("model_year");
                String model = resultSet.getString("model");

                int driverId = resultSet.getInt("driver_id");
                int driverAge = resultSet.getInt("driver_age");
                String driverName = resultSet.getString("driver_name");
                String driverQualification = resultSet.getString("qualification");

                final Driver driver = new Driver(driverId, driverName, driverAge, driverQualification);

                final Track track = new Track(id, modelYear, model, driver);

                driver.addTrack(track);

                driverList.add(driver);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driverList;
    }

}
