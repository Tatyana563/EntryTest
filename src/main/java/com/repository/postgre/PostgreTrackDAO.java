package com.repository.postgre;

import com.domain.Driver;
import com.domain.Track;

import com.repository.TrackDAO;
import com.repository.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Work with POSTGRE
 */
public class PostgreTrackDAO implements TrackDAO {

    @Override
    public void deleteByYearAndModel(int year, String model) {
        Connection connection = ConnectionFactory.getConnection();

        String query = "DELETE FROM Track t WHERE t.model_year = ? AND t.model = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, year);
            statement.setString(2, model);
            System.out.println("Delete " + statement.executeUpdate() + " rows");
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateModelByModelYear(int year, String model) {
        Connection connection = ConnectionFactory.getConnection();
        String query = "UPDATE track SET model=?"  +
                " WHERE model_year = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, model);
            statement.setInt(2, year);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Track track) {
        Connection connection = ConnectionFactory.getConnection();

        String insertQuery =
                "INSERT INTO Track(model_year, model, driver_fk_id) VALUES(?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            statement.setInt(1, track.getModelYear());
            statement.setString(2, track.getModel());
            statement.setInt(3, track.getDriver().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Track track) {
        String insertQuery = "" +
                "UPDATE track SET model_year = ?, model=?" +
                " WHERE track.track_id = ?;";

        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(insertQuery)) {

            statement.setInt(1, track.getModelYear());
            statement.setString(2, track.getModel());
            statement.setInt(3, track.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int trackId) {
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(
                "DELETE FROM track as t WHERE t.track_id=?")) {
            statement.setInt(1, trackId);
            int result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Track> findAllByYear(int year) {
        List<Track> trackList = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(
                "SELECT * FROM track AS t " +
                        "INNER JOIN Driver AS d " +
                        "ON t.driver_fk_id = d.driver_id " +
                        "WHERE t.model_year=?"
        )) {
            statement.setInt(1, year);

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

                trackList.add(track);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(trackList);
        return trackList;
    }
}
