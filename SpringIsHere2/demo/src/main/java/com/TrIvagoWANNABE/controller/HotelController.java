package com.TrIvagoWANNABE.controller;

import com.TrIvagoWANNABE.model.AvailableRoom;
import com.TrIvagoWANNABE.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @GetMapping("/search")
    public List<AvailableRoom> searchAvailableRooms(
            @RequestParam int roomType,
            @RequestParam double minPrice,
            @RequestParam double maxPrice,
            @RequestParam double maxDistance,
            @RequestParam double latitude,
            @RequestParam double longitude) {
        return hotelService.searchAvailableRoomsByCriteria(roomType, minPrice, maxPrice, maxDistance, latitude, longitude);
    }
    @PostMapping("/{hotelId}/book/{roomNumber}")
    public String bookRoom(@PathVariable int hotelId, @PathVariable int roomNumber) {
        boolean success = hotelService.bookRoom(hotelId, roomNumber);
        return success ? "Room booked successfully." : "Failed to book room.";
    }
    @PostMapping("/{hotelId}/cancel/{roomNumber}")
    public String cancelBooking(@PathVariable int hotelId, @PathVariable int roomNumber) {
        boolean success = hotelService.cancelBooking(hotelId, roomNumber);
        return success ? "Booking canceled successfully." : "Failed to cancel booking.";
    }
    @PostMapping("/{hotelId}/feedback/{roomNumber}")
    public String addFeedback(@PathVariable int hotelId, @PathVariable int roomNumber, @RequestParam String feedback) {
        boolean success = hotelService.addFeedback(hotelId, roomNumber, feedback);
        return success ? "Feedback added successfully." : "Failed to add feedback.";
    }
    @GetMapping("/{hotelId}/feedback/{roomNumber}")
    public List<String> getFeedback(@PathVariable int hotelId, @PathVariable int roomNumber) {
        return hotelService.getFeedback(hotelId, roomNumber);
    }
}
