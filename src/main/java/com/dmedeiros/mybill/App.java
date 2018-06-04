package com.dmedeiros.mybill;

import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserService userService) {
        return (args) -> {

            User user = new User();
            user.setName("diego medeiros");
            user.setLogin("aian");
            user.setPassword("1234");

            //save
            userService.prepareToAndSave(user);

            //check
//            user.setLogin("");
            boolean exists = userService.verifyIfExists(user);
            System.out.println(exists);

            //delete
//            user.setLogin("xx");
//            userService.verifyAndRemove(user);

            //alter
//            user.setLogin("xx");
            System.out.println("last access: " + user.getLastAccess());
            user.setLastAccess(LocalDate.now());
            userService.verifyAndUpdate(user);
            System.out.println("last access: " + user.getLastAccess());

        };
    }



}
