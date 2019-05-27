package com.example.demo.Controller;

import com.example.demo.Config.Mailer;
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

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
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
    @Autowired
    Mailer mailer;

    @GetMapping("/")
    public String index() {
        logger.info("index called.");
        return "index";
    }

    @GetMapping("/adminBooking")
    public String adminBooking(Model model) throws SQLException {
        model.addAttribute("booking", new Booking());
        model.addAttribute("allAvailable", bookingService.getAllAvailable());
        model.addAttribute("allConfirmed", bookingService.getAllConfirmed());
        model.addAttribute("allBooked", bookingService.getAllBooked());
        model.addAttribute("allBookings", bookingService.getAllBooking());



        logger.info("admin page called by: "+myAccessDeniedHandler.getUserName());
        return "adminBooking";

    }

    @GetMapping("/adminCreateBooking")
    public String adminCreateBooking(Model model) throws SQLException {
        model.addAttribute("booking", new Booking());
        model.addAttribute("allAvailable", bookingService.getAllAvailable());
        model.addAttribute("allConfirmed", bookingService.getAllConfirmed());
        model.addAttribute("allBooked", bookingService.getAllBooked());
        model.addAttribute("allBookings", bookingService.getAllBooking());



        logger.info("admin page called by: "+myAccessDeniedHandler.getUserName());
        return "adminCreateBooking";

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
        logger.info("vi kom til: "+tempId);
        logger.info("vi kom til idforbook: "+idForBook);

        return "/bookDetails";
    }

    @PostMapping("/bookDetails")
    public String bookDetails(@ModelAttribute Booking booking) throws SQLException {


        // ANDERS MANGLER
        bookingService.bookTime(booking, tempId);
        logger.info("HUZZAAAA");

        return "redirect:/bookTime";
    }

    @GetMapping("/confirm/{id}")
    public String confirm(@PathVariable("id") int idForConfirm) throws SQLException, AddressException, MessagingException, IOException {

        String to = bookingService.getBookingById(idForConfirm).getMail();
        String name = bookingService.getBookingById(idForConfirm).getName();
        String start = bookingService.getBookingById(idForConfirm).getBookingStart();
        String end = bookingService.getBookingById(idForConfirm).getBookingEnd();

        String context = mailer.mailTemplate(name, start, end, idForConfirm);

        mailer.sendmail(to, context);
        bookingService.confirm(idForConfirm);
        logger.info("mail sent");

        return "redirect:/adminBooking";
    }

    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable("id") int idForCancel)throws SQLException, AddressException, MessagingException, IOException {

        logger.info(idForCancel+" has been cancled, such a shame");

        String end = bookingService.getBookingById(idForCancel).getBookingEnd();
        String start = bookingService.getBookingById(idForCancel).getBookingStart();
        String name = bookingService.getBookingById(idForCancel).getName();

        String context = mailer.canceltemplate(end, start, idForCancel, name);
        mailer.sendmail("steffens.biks@gmail.com", context);



        bookingService.cancel(idForCancel);

        return "redirect:/cancelSuccess/"+idForCancel;
    }

    @GetMapping("/cancelSuccess/{id}")
    public String cancelSuccess(@PathVariable("id") int idForCancel, Model model){

        Booking booking = new Booking();
        booking.setId(idForCancel);

        model.addAttribute("booker", booking);


        return "/cancelSuccess";
    }


}