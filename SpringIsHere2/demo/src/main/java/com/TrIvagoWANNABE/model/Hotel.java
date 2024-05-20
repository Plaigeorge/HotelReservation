package com.TrIvagoWANNABE.model;

import java.util.List;

public class Hotel {
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private List<Room> rooms;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public List<Room> getRooms() {
        return rooms;
    }
}
