package com.TrIvagoWANNABE.service;

import com.TrIvagoWANNABE.model.AvailableRoom;
import com.TrIvagoWANNABE.model.Hotel;
import com.TrIvagoWANNABE.model.Room;
import com.TrIvagoWANNABE.JsonReader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private List<Hotel> hotels;

    public HotelService() {
        this.hotels = JsonReader.loadHotels();
    }

    public boolean bookRoom(int hotelId, int roomNumber) {
        Optional<Hotel> hotel = findHotelById(hotelId);
        if (hotel.isPresent()) {
            for (Room room : hotel.get().getRooms()) {
                if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                    room.setAvailable(false);
                    JsonReader.saveHotels(hotels);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean cancelBooking(int hotelId, int roomNumber) {
        Optional<Hotel> hotel = findHotelById(hotelId);
        if (hotel.isPresent()) {
            for (Room room : hotel.get().getRooms()) {
                if (room.getRoomNumber() == roomNumber && !room.isAvailable()) {
                    room.setAvailable(true);
                    JsonReader.saveHotels(hotels);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean addFeedback(int hotelId, int roomNumber, String feedback) {
        Optional<Hotel> hotel = findHotelById(hotelId);
        if (hotel.isPresent()) {
            for (Room room : hotel.get().getRooms()) {
                if (room.getRoomNumber() == roomNumber) {
                    room.addFeedback(feedback);
                    JsonReader.saveHotels(hotels);
                    return true;
                }
            }
        }
        return false;
    }
    public List<String> getFeedback(int hotelId, int roomNumber) {
        Optional<Hotel> hotel = findHotelById(hotelId);
        if (hotel.isPresent()) {
            for (Room room : hotel.get().getRooms()) {
                if (room.getRoomNumber() == roomNumber) {
                    return room.getFeedback();
                }
            }
        }
        return new ArrayList<>();
    }
    public List<AvailableRoom> searchAvailableRoomsByCriteria(int roomType, double minPrice, double maxPrice, double maxDistance, double latitude, double longitude) {
        List<AvailableRoom> availableRooms = new ArrayList<>();

        for (Hotel hotel : hotels) {
            for (Room room : hotel.getRooms()) {
                if (room.getType() == roomType && room.getPrice() >= minPrice && room.getPrice() <= maxPrice && room.isAvailable()) {
                    double distance = calculateDistance(latitude, longitude, hotel.getLatitude(), hotel.getLongitude());
                    if (distance <= maxDistance) {
                        availableRooms.add(new AvailableRoom(hotel.getId(), hotel.getName(), room, distance));
                    }
                }
            }
        }
        return availableRooms;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; //raza Terra
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // convertire KM
        return distance;
    }
    private Optional<Hotel> findHotelById(int hotelId) {
        return hotels.stream().filter(h -> h.getId() == hotelId).findFirst();
    }
}
