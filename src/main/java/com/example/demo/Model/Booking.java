package com.example.demo.Model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Booking {


    private int id;
    private String bookingStart;
    private String bookingEnd;
    private String status;
    private String name;
    private String adresse;
    private String mail;
    private int telefon;
    private int kvdm;

    public Booking(int id, String  bookingStart, String bookingEnd, String status, String name, String adresse, String mail, int telefon, int kvdm) {
        this.id = id;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
        this.status = status;
        this.name = name;
        this.adresse = adresse;
        this.mail = mail;
        this.telefon = telefon;
        this.kvdm = kvdm;
    }

    public Booking() {
    }





    public Booking(String name) {


        this.bookingStart = "2019-06-01T12:00:00";
        this.bookingEnd = "2019-06-01T14:00:00";



        this.name = name;
    }

    public Booking(String bookingStart, String bookingEnd) {
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
        this.status = "available";

    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingStart=" + bookingStart +
                ", bookingEnd=" + bookingEnd +
                ", status=" + status +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMail() {
        return mail;
    }

    public int getTelefon() {
        return telefon;
    }

    public int getKvdm() {
        return kvdm;
    }

    public String getBookingStart() {
        return bookingStart;
    }

    public void setBookingStart(String bookingStart) {
        this.bookingStart = bookingStart;
    }

    public String  getBookingEnd() {
        return bookingEnd;
    }

    public void setBookingEnd(String bookingEnd) {
        this.bookingEnd = bookingEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public void setKvdm(int kvdm) {
        this.kvdm = kvdm;
    }
}
