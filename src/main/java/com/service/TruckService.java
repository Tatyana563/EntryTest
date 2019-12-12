package com.service;

import com.dto.TruckDTO;

import java.util.List;

public interface TruckService<T> extends CRUDService<T> {
    List<T> findAllByYear(int year);
    void deleteByYearAndModel(int year, String model);
    void updateModelByModelYear(int year, String model);
    List<TruckDTO> findOnlyTrucksByYear(int year);
}
