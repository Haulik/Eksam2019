package com.example.demo;

import com.example.demo.Config.WebSecurityConfig;
import com.example.demo.Controller.MainController;
import com.example.demo.Controller.MyAccessDeniedHandler;
import com.example.demo.Model.Booking;
import com.example.demo.Repository.BookingRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    MainController mainController;
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    WebSecurityConfig webSecurityConfig;
    @Autowired
    BookingRepo bookingRepo;





    @Test
    public void login() {
        assertEquals("/login", mainController.login());
    }

    @Test
    public void index() {
        assertEquals("index", mainController.index());
    }

    //tester for at man ikke kan komme på admin
    //@Test
    //public void admin() throws Exception{
    //    assertEquals("/admin", mainController.admin() );
    //    assertNotEquals(myAccessDeniedHandler.getAuth() !=null, mainController.admin());
    //}

    @Test
    public void createBooking() throws SQLException {
        bookingRepo.createBooking(new Booking("TestBook1"));
        assertEquals("TestBook1", bookingRepo.getBookingByName("TestBook1").get(0).getName());

    }

    @Test
    public void getBookingById() throws SQLException {
        assertEquals("Tester-Egon", bookingRepo.getBookingById(1).get(0).getName());
    }

    @Test
    public void getAllBooking() throws SQLException {
        assertEquals("Tester-Egon", bookingRepo.getAllBooking().get(0).getName());
    }

    @Test
    public void getAllAvailable() throws SQLException {
        assertEquals("Tester-Alf", bookingRepo.getAllAvailable().get(0).getName());
    }

    @Test
    public void getAllConfirmed() throws SQLException {
        assertEquals("Tester-Egon", bookingRepo.getAllConfirmed().get(0).getName());
    }

    @Test
    public void getAllBooked() throws SQLException {
        assertEquals("Tester-Torben", bookingRepo.getAllBooked().get(0).getName().toString());
    }

    /*@Test
    public void cancelBooking() throws SQLException {
        assertEquals("available", bookingRepo.cancelBooking(4)
        assertEquals("available", bookingRepo.cancelBooking(4).toString());

    }*/



    @Test
    public void customerBook() throws SQLException{
        Booking booking = new Booking();
        booking.setStatus("booked");
        bookingRepo.book(booking, bookingRepo.getBookingByName("TestBook1").get(0).getId());
        assertEquals("booked", bookingRepo.getBookingByName("TestBook1").get(0).getStatus());
    }

    @Test
    public void deleteBooking() throws SQLException{


        bookingRepo.deleteBooking(bookingRepo.getBookingByName("TestBook1").get(0).getId());
        assertEquals((bookingRepo.getBookingByName("TestBook1").get(0).getId()),bookingRepo.deleteBooking(bookingRepo.getBookingByName("TestBook1").get(0).getId()));
    }

    // test kvdrm pris




}
