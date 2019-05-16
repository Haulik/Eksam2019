package com.example.demo.Controller;

import com.example.demo.Model.Booking;
import com.example.demo.Service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;


@org.springframework.stereotype.Controller
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);
    private Booking booking;
    private int tempId;

    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    BookingService bookingService;

    @GetMapping("/")
    public String index() {
        logger.info("index called.");
        return "index";
    }


    @GetMapping("/calender")
    public String calender() {

        return "calender";

    }

    @GetMapping("/AdminTest")
    public String AdminTest(Model model) throws SQLException {
        model.addAttribute("booking", new Booking());
        model.addAttribute("allAvailable", bookingService.getAllAvailable());
        model.addAttribute("allConfirmed", bookingService.getAllConfirmed());
        model.addAttribute("allBooked", bookingService.getAllBooked());
        model.addAttribute("allBookings", bookingService.getAllBooking());



        logger.info("admin page called by: "+myAccessDeniedHandler.getUserName());
        return "AdminTest";

    }

    @GetMapping("/login")
    public String login() {
        logger.info("/login called.");
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        logger.info("user unable to log in");
        return "/error/403";
    }

    @GetMapping("/admin")
    public String admin(Model model) throws SQLException {

        model.addAttribute("booking", new Booking());
        model.addAttribute("allAvailable", bookingService.getAllAvailable());
        model.addAttribute("allConfirmed", bookingService.getAllConfirmed());
        model.addAttribute("allBooked", bookingService.getAllBooked());
        model.addAttribute("allBookings", bookingService.getAllBooking());



        logger.info("admin page called by: "+myAccessDeniedHandler.getUserName());
        return "admin";
    }

    @PostMapping("/admin")
    public String admin (@ModelAttribute Booking booking){

        // har bøvl med input typen ..........

        bookingService.createBooking(booking);
        logger.info("booking created - start time: "+booking.getBookingStart());

        return "redirect:/admin";
    }

    @GetMapping("/bookTime")
    public String bookTime (Model model) throws SQLException {

        logger.info("bookTime called.");

        model.addAttribute("bookings", bookingService.getAllAvailable());

        return "bookTime";
    }

    @GetMapping("/bookDetails/{book}")
    public String bookDetails(@PathVariable("book") int idForBook, Model model){

        model.addAttribute("booker", new Booking());

        tempId = idForBook;

        return "bookTime";
    }

    @PostMapping("/bookDetails")
    public String bookDetails(@ModelAttribute Booking booking) throws SQLException {


        // ANDERS MANGLER
        bookingService.bookTime(booking, tempId);

        return "redirect:/bookTime";
    }







}
