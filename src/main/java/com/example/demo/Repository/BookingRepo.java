package com.example.demo.Repository;

import com.example.demo.Model.Booking;

import java.sql.SQLException;
import java.util.List;

public interface BookingRepo {

    void createBooking(Booking booking);
    List<Booking> getAllBooking() throws SQLException;
    List<Booking> getAllAvailable() throws SQLException;
    List<Booking> getAllConfirmed() throws SQLException;
    List<Booking> getAllBooked() throws SQLException;
    void cancelBooking(int id) throws SQLException;
    int deleteBooking(int id);
    List<Booking> getBookingByName(String name) throws SQLException;
    List<Booking> getBookingById(int id) throws SQLException;
    void book(Booking booking, int id);
    void confirm(int id);

}
