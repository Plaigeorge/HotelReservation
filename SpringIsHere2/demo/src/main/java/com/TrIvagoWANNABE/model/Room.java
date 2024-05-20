package com.TrIvagoWANNABE.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomNumber;
    private int type;
    private double price;

    @JsonProperty("isAvailable")
    private boolean available;
    private List<String> feedback;
    public Room() {
        this.feedback = new ArrayList<>();
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public int getType() {
        return type;
    }
    public double getPrice() {
        return price;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public List<String> getFeedback() {
        return feedback;
    }
    public void addFeedback(String feedback) {
        this.feedback.add(feedback);
    }
}
