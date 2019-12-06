package com.dto;

import com.domain.Track;

public class TruckDTO {
    private int id;
    private int modelYear;
    private String model;

    public TruckDTO(Track track) {
        this.id = track.getId();
        this.modelYear = track.getModelYear();
        this.model =track.getModel();
    }

    public TruckDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
