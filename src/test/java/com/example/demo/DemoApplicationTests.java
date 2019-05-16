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
    public void createBooking() {
        bookingRepo.createBooking(new Booking("TestBook1"));
        assertEquals("TestBook1", bookingRepo.getBookingByName("TestBook1"));
    }

    @Test
    public void getAllBooking() {
        assertEquals("Tester-Alf", bookingRepo.getAllBooking().get(0).getName());
    }

    @Test
    public void getAllAvailable() {
        assertEquals("Tester-Alf", bookingRepo.getAllAvailable().get(0).getName());
    }

    @Test
    public void getAllConfirmed() {
        assertEquals("Tester-Egon", bookingRepo.getAllConfirmed().get(0).getName());
    }

    @Test
    public void getAllBooked() {
        assertEquals("Tester-Torben", bookingRepo.getAllBooked().get(0).getName().toString());
    }

    @Test
    public void cancelBooking() {
        assertEquals("available", bookingRepo.cancelBooking(4).toString());
    }

    @Test
    public void deleteBooking() {

        bookingRepo.deleteBooking(4);
        assertEquals(4,bookingRepo.deleteBooking(4));
    }

    // test kvdrm pris




}
