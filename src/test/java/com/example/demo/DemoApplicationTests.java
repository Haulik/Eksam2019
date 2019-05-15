package com.example.demo;

import com.example.demo.Controller.MainController;
import com.example.demo.Controller.MyAccessDeniedHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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



    @Test
    public void login() {
        assertEquals("/login", mainController.login());
    }

    @Test
    public void index() {
        assertEquals("index", mainController.index());
    }

    //tester for at man ikke kan komme på admin
    @Test
    public void admin() {
        assertEquals("/admin", mainController.admin() );
        assertNotEquals(myAccessDeniedHandler.getAuth() !=null, mainController.admin());
    }

}
