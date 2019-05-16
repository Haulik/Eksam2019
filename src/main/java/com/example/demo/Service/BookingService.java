package com.example.demo.Service;

import com.example.demo.Model.Booking;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.print.Book;
import java.util.List;

public interface BookingService {

    void createBooking(Booking booking);
    List<Booking> getAllAvailable();
    List<Booking> getAllConfirmed();
    List<Booking> getAllBooked();
    List<Booking> getAllBooking();
    void bookTime(Booking booking, int id);
}
