package com.service;

import com.domain.Track;

import java.util.List;

public interface TruckService<T> extends CRUDService<T> {
    List<T> findAllByYear(int year);
    void deleteByYearAndModel(int year, String model);
    void updateModelByModelYear(int year, String model);
    public List<Track> findOnlyTrucksByYear(int year);
}
