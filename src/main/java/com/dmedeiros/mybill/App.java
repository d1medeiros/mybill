package com.dmedeiros.mybill;

import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.model.BillType;
import com.dmedeiros.mybill.bill.service.BillAndWalletService;
import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserService userService, BillAndWalletService billAndWalletService) {
        return (args) -> {

            User user = new User();
            user.setName("Diego");
            user.setLogin("aian");
            user.setPassword("1234");

            User userSaved = userService.prepareToAndSave(user);

            Bill bill = new Bill();
            bill.setBillType(BillType.GASTOS_NORMAL);
            bill.setPayday(LocalDate.now());
            bill.setPaid(true);
            bill.setPrice(200.22);
            bill.setName("pizza");
            billAndWalletService.save(userSaved.getWallet(), bill);


        };
    }


}
