package com.service.impl;

import com.domain.Track;
import com.repository.TrackDAO;
import com.repository.postgre.PostgreTrackDAO;
import com.service.TruckService;

import java.util.List;

public class TrackService implements TruckService<Track> {

    public static final TrackDAO TRACK_DAO = new PostgreTrackDAO();

    @Override
    public void save(Track track) {
        TRACK_DAO.save(track);
    }

    @Override
    public void update(Track track) {
        TRACK_DAO.update(track);
    }

    @Override
    public void deleteById(int id) {
        TRACK_DAO.deleteById(id);
    }

    @Override
    public List<Track> findAllByYear(int year) {
        return TRACK_DAO.findAllByYear(year);
    }

    @Override
    public void deleteByYearAndModel(int year, String model) {
        TRACK_DAO.deleteByYearAndModel(year, model);
    }

    @Override
    public void updateModelByModelYear(int year, String model) {
        TRACK_DAO.updateModelByModelYear(year, model);
    }

}
