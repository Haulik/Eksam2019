package com.example.demo;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainControllerTest {

    MainController mainController = new MainController();

    @Test
    public void index() {
        assertEquals("index", mainController.index());
    }
}