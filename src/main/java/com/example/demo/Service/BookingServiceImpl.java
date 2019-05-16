package com.example.demo.Service;

import com.example.demo.Model.Booking;
import com.example.demo.Repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Booking> getAllAvailable(){
        return bookingRepo.getAllAvailable();
    }

    @Override
    public List<Booking> getAllConfirmed(){
        return bookingRepo.getAllConfirmed();
    }

    @Override
    public List<Booking> getAllBooked(){
        return bookingRepo.getAllBooked();
    }

    @Override
    public List<Booking> getAllBooking(){
        return bookingRepo.getAllBooking();
    }

    @Override
    public void bookTime(Booking booking, int id){

        //sikre at den tid ikke allerede er blevet booket
        if (bookingRepo.getBookingById(id).get(0).getStatus().equals("available")){
            bookingRepo.book(booking, id);
        }

    }
}
