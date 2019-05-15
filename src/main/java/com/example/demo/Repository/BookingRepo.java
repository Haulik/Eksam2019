package com.example.demo.Repository;

import com.example.demo.Model.Booking;
import com.example.demo.Model.State;

import java.util.List;

public interface BookingRepo {

    void createBooking(Booking booking);
    List<Booking> getAllBooking();
    List<Booking> getAllAvailable();
    List<Booking> getAllConfirmed();
    List<Booking> getAllBooked();
    String cancelBooking(int id);
    int deleteBooking(int id);
    Booking getBookingByName(String name);
    Booking getBookingById(int id);
    void book(Booking booking, int id);

}
