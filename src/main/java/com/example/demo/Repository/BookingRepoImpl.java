package com.example.demo.Repository;

import com.example.demo.Model.Booking;
import com.example.demo.Model.State;
import com.sun.rowset.internal.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepoImpl implements BookingRepo {

    // template
    // insert into visit values(default, '01-06-2019:12:00:00', '01-06-2019:14:00:00', 'available', 'Tester-Alf', '321 Tester Street', 'evil@tester.abe', 12345678, 80);

    @Autowired
    JdbcTemplate template;

    @Override
    public void createBooking(Booking booking) {

        String sql = "INSERT INTO gulv.visit (idBooking, bookStart, bookEnd, bookState) values(default, ?, ?, 'available')";

        this.template.update(sql, booking.getBookingStart(), booking.getBookingEnd());

    }



    @Override
    public List<Booking> getAllBooking() {

        String sql = "SELECT * FROM gulv.visit";

            return this.template.query(sql, new ResultSetExtractor<List<Booking>>() {
                @Override
                public List<Booking> extractData(ResultSet rs) throws SQLException, DataAccessException {
                    int id;
                    String name, adresse, mail, bookingStart, bookingEnd, state;
                    int phone, size;

                    ArrayList<Booking> allBookings = new ArrayList<>();

                    while (rs.next()){
                        id = rs.getInt("idBooking");
                        bookingStart = rs.getString("bookStart");
                        bookingEnd = rs.getString("bookEnd");
                        state = rs.getString("bookState");
                        name = rs.getString("bookerName");
                        adresse = rs.getString("bookerAdresse");
                        mail = rs.getString("bookerMail");
                        phone = rs.getInt("bookerPhone");
                        size = rs.getInt("bookerSize");

                        // cast string til localDateTime
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                        LocalDateTime bookingStartDT = LocalDateTime.parse(bookingStart, formatter);
                        LocalDateTime bookingEndDT = LocalDateTime.parse(bookingEnd, formatter);




                        allBookings.add(new Booking(id, bookingStartDT, bookingEndDT, state, name, adresse, mail, phone, size));


                    }

                    return allBookings;
                }
            });


    }

    public List<Booking> byResultSet(String sql){

        return this.template.query(sql, new ResultSetExtractor<List<Booking>>() {
            @Override
            public List<Booking> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int id;
                String name, adresse, mail, bookingStart, bookingEnd, state;
                int phone, size;

                ArrayList<Booking> bookings = new ArrayList<>();

                while (rs.next()){
                    id = rs.getInt("idBooking");
                    bookingStart = rs.getString("bookStart");
                    bookingEnd = rs.getString("bookEnd");
                    state = rs.getString("bookState");
                    name = rs.getString("bookerName");
                    adresse = rs.getString("bookerAdresse");
                    mail = rs.getString("bookerMail");
                    phone = rs.getInt("bookerPhone");
                    size = rs.getInt("bookerSize");

                    // cast string til localDateTime
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    LocalDateTime bookingStartDT = LocalDateTime.parse(bookingStart, formatter);
                    LocalDateTime bookingEndDT = LocalDateTime.parse(bookingEnd, formatter);


                    bookings.add(new Booking(id, bookingStartDT, bookingEndDT, state, name, adresse, mail, phone, size));


                }

                return bookings;
            }
        });
    }


    public List<Booking> getAllWithVariants(String variant){

        String sqlTo = "\'"+variant+"\'";

        String sql = "SELECT * FROM gulv.visit WHERE bookState = "+sqlTo;


        return this.template.query(sql, new ResultSetExtractor<List<Booking>>() {
            @Override
            public List<Booking> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int id;
                String name, adresse, mail, bookingStart, bookingEnd, state;
                int phone, size;

                ArrayList<Booking> bookings = new ArrayList<>();

                while (rs.next()){
                    id = rs.getInt("idBooking");
                    bookingStart = rs.getString("bookStart");
                    bookingEnd = rs.getString("bookEnd");
                    state = rs.getString("bookState");
                    name = rs.getString("bookerName");
                    adresse = rs.getString("bookerAdresse");
                    mail = rs.getString("bookerMail");
                    phone = rs.getInt("bookerPhone");
                    size = rs.getInt("bookerSize");

                    // cast string til localDateTime
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    LocalDateTime bookingStartDT = LocalDateTime.parse(bookingStart, formatter);
                    LocalDateTime bookingEndDT = LocalDateTime.parse(bookingEnd, formatter);

                    bookings.add(new Booking(id, bookingStartDT, bookingEndDT, state, name, adresse, mail, phone, size));


                }

                return bookings;
            }
        });



    }

    @Override
    public List<Booking> getAllAvailable() {

        return getAllWithVariants("available");

    }

    @Override
    public List<Booking> getAllConfirmed() {

        return getAllWithVariants("confirmed");
    }

    @Override
    public List<Booking> getAllBooked() {

        return getAllWithVariants("booked");
    }

    @Override
    public String cancelBooking(int id) {

        String sql = "UPDATE gulv.visit SET bookState = 'available' WHERE idBooking =?";

        this.template.update(sql, id);

        return getAllBooking().get(id-1).getStatus();
    }

    @Override
    public int deleteBooking(int id) {

        String sql = "DELETE FROM gulv.visit where idBooking =?";

        this.template.update(sql, id);

        return id;
    }

    @Override
    public Booking getBookingByName(String name) {

        String sql = "SELECT * FROM gulv.visit where bookerName = ?";
        RowMapper<Booking> rm = new BeanPropertyRowMapper<>(Booking.class);

        Booking searched = template.queryForObject(sql, rm, name);
        return searched;
    }

    @Override
    public Booking getBookingById(int id) {

        String sql = "SELECT * FROM gulv.visit where idBooking = ?";
        RowMapper<Booking> rm = new BeanPropertyRowMapper<>(Booking.class);

        Booking searched = template.queryForObject(sql, rm, id);
        return searched;
    }

    @Override
    public void book(Booking booking, int id){

        String sql ="UPDATE gulv.visit SET (bookState, bookerName, bookerAdresse, bookerMail, bookerPhone, bookerSize) VALUES('booked', ?, ?, ?, ?, ?) WHERE idBookings = ?";
        this.template.update(sql, booking.getName(), booking.getAdresse(), booking.getMail(), booking.getTelefon(), booking.getKvdm(), id);
    }
}
