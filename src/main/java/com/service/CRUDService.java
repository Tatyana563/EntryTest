package com.service;

import java.util.List;

public interface CRUDService<T> {
    void save(T t);
    void update(T t);
    void deleteById(int id);
    List<T> findAllByYear(int year);
    void deleteByYearAndModel(int year, String model);
    void updateModelByModelYear(int year, String model);
}
