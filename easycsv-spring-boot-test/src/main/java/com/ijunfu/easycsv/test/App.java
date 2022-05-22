package com.ijunfu.easycsv.test;

import com.ijunfu.easycsv.autoconfig.annotation.PathLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@RestController
@SpringBootApplication
public class App 
{

    @PathLog(name = "home", notes = "go home")
    @GetMapping("/")
    public String home() {
        return "Welcome to you!";
    }

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
