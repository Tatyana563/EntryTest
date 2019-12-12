package com.repository;

import com.domain.Track;
import com.dto.TruckDTO;

import java.util.List;

/**
 * Created by User on 018 18.11.19.
 */
public interface TrackDAO {
    void save(Track track);
    void update(Track track);
    void deleteById(int trackId);
    List<Track> findAllByYear(int year);
    void deleteByYearAndModel(int year, String model);
    void updateModelByModelYear(int year, String model);
    public List<TruckDTO> findOnlyTrucksByYear(int year);
}
