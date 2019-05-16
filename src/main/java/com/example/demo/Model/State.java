package com.example.demo.Model;

import org.springframework.stereotype.Component;

@Component
public class State {

    private String state;

    private  String booked = "booked";
    private  String available = "available";
    private  String confirmed = "confirmed";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAvailable(){
        this.state=available;
    }

    public void setBooked(){
        this.state=booked;
    }

    public void setConfirmed(){
        this.state=confirmed;
    }



    public String getBooked() {
        return booked;
    }

    public String getAvailable() {
        return available;
    }

    public String getConfirmed() {
        return confirmed;
    }
}
