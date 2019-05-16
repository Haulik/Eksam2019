package com.example.demo.Model;


import java.time.LocalDateTime;



public class Booking {


    private int id;
    private LocalDateTime bookingStart;
    private LocalDateTime bookingEnd;
    private String status;
    private String name;
    private String adresse;
    private String mail;
    private int telefon;
    private int kvdm;

    public Booking(int id, LocalDateTime bookingStart, LocalDateTime bookingEnd, String status, String name, String adresse, String mail, int telefon, int kvdm) {
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
        this.bookingStart = LocalDateTime.now().withNano(0);
        this.bookingEnd = LocalDateTime.now().withNano(0);
        this.name = name;
    }

    public Booking(LocalDateTime bookingStart, LocalDateTime bookingEnd) {
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

    public LocalDateTime getBookingStart() {
        return bookingStart;
    }

    public void setBookingStart(LocalDateTime bookingStart) {
        this.bookingStart = bookingStart.withNano(0);
    }

    public LocalDateTime getBookingEnd() {
        return bookingEnd;
    }

    public void setBookingEnd(LocalDateTime bookingEnd) {
        this.bookingEnd = bookingEnd.withNano(0);
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
