package com.example.demo.Service;

import com.example.demo.Model.Booking;
import com.example.demo.Repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepo bookingRepo;

    @Override
    public void createBooking(Booking booking){
        bookingRepo.createBooking(booking);
    }

    @Override
    public List<Booking> getAllAvailable() throws SQLException {
        return bookingRepo.getAllAvailable();
    }

    @Override
    public List<Booking> getAllConfirmed() throws SQLException {
        return bookingRepo.getAllConfirmed();
    }

    @Override
    public List<Booking> getAllBooked() throws SQLException {
        return bookingRepo.getAllBooked();
    }

    @Override
    public List<Booking> getAllBooking() throws SQLException {
        return bookingRepo.getAllBooking();
    }

    @Override
    public void bookTime(Booking booking, int id) throws SQLException {

        //sikre at den tid ikke allerede er blevet booket
        if (bookingRepo.getBookingById(id).get(0).getStatus().equals("available")){
            bookingRepo.book(booking, id);
        }
    }

    @Override
    public void confirm(int id){

        /// LOGIC ???
        bookingRepo.confirm(id);
    }

    @Override
    public Booking getBookingById(int id) throws SQLException{
        return bookingRepo.getBookingById(id).get(0);
    }

    @Override
    public void cancel(int id) throws SQLException {
        bookingRepo.cancelBooking(id);
    }
}
