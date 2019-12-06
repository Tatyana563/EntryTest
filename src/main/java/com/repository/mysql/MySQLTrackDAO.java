package com.repository.mysql;

import com.domain.Track;
import com.repository.TrackDAO;

import java.util.List;


public class MySQLTrackDAO implements TrackDAO {

    @Override
    public void save(Track track) {

    }

    @Override
    public void update(Track track) {

    }

    @Override
    public void deleteById(int trackId) {

    }

    @Override
    public List<Track> findAllByYear(int year) {
        return null;
    }

    @Override
    public void deleteByYearAndModel(int year, String model) {

    }

    @Override
    public void updateModelByModelYear(int year, String model) {

    }

    @Override
    public List<Track> findOnlyTrucksByYear(int year) {
        return null;
    }
}
