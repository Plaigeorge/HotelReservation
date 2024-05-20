package com.TrIvagoWANNABE.model;

public class AvailableRoom {
    private int hotelId;
    private String hotelName;
    private int roomNumber;
    private double price;
    private double distance;

    public AvailableRoom(int hotelId, String hotelName, Room room, double distance) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.roomNumber = room.getRoomNumber();
        this.price = room.getPrice();
        this.distance = distance;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
