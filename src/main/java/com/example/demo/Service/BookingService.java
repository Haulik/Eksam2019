package com.example.demo.Service;

import com.example.demo.Model.Booking;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;

public interface BookingService {

    void createBooking(Booking booking);
    List<Booking> getAllAvailable() throws SQLException;
    List<Booking> getAllConfirmed() throws SQLException;
    List<Booking> getAllBooked() throws SQLException;
    List<Booking> getAllBooking() throws SQLException;
    void bookTime(Booking booking, int id) throws SQLException;
    void confirm(int id);
    Booking getBookingById(int id) throws SQLException;
    void cancel(int id) throws SQLException;

}

