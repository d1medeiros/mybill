package com.dmedeiros.mybill;

import com.dmedeiros.mybill.bill.model.BillType;
import com.dmedeiros.mybill.bill.model.Paid;
import com.dmedeiros.mybill.bill.model.Schedule;
import com.dmedeiros.mybill.bill.service.PaidAndWalletService;
import com.dmedeiros.mybill.bill.service.ScheduleAndWalletService;
import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.service.UserService;
import com.dmedeiros.mybill.util.SecurityToken;
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

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/user").allowedOrigins("http://localhost:3000");
////                registry.addMapping("/user").allowedMethods("PUT");
////                registry.addMapping("/user").allowedHeaders("*");
//            }
//        };
//    }

    @Bean
    public CommandLineRunner demo(UserService userService, PaidAndWalletService paidAndWalletService, ScheduleAndWalletService scheduleAndWalletService) {
        return (args) -> {

            User user = new User();
            user.setId(1l);
            user.setName("Diego");
            user.setLogin("diego");
            user.setPassword("12345");

            User userSaved = userService.prepareToAndSave(user);

            System.out.println(SecurityToken.generateHash(user));

            LocalDate now = LocalDate.now();

            Paid paid = new Paid();
            paid.setName("pizza");
            paid.setPrice(200.22);
            paid.setPayday(now);
            paid.setBillType(BillType.GASTOS);
            paidAndWalletService.save(userSaved.getWallet(), paid);

            paid = new Paid();
            paid.setName("pao");
            paid.setPrice(20.20);
            paid.setPayday(now);
            paid.setBillType(BillType.GASTOS);
            paidAndWalletService.save(userSaved.getWallet(), paid);

            paid = new Paid();
            paid.setName("comida japonesa");
            paid.setPrice(320.20);
            paid.setPayday(now);
            paid.setBillType(BillType.GASTOS);
            paidAndWalletService.save(userSaved.getWallet(), paid);

            paid = new Paid();
            paid.setName("freela");
            paid.setPrice(800.20);
            paid.setPayday(now);
            paid.setBillType(BillType.GANHO);
            paidAndWalletService.save(userSaved.getWallet(), paid);

            paid = new Paid();
            paid.setName("freela");
            paid.setPrice(800.20);
            now = now.minusMonths(1);
            paid.setPayday(now);
            paid.setBillType(BillType.GANHO);
            paidAndWalletService.save(userSaved.getWallet(), paid);








            Schedule schedule = new Schedule();
            schedule.setName("carro");
            schedule.setPrice(800.00);
            schedule.setDayToPay(10);
            schedule.setBillType(BillType.GASTOS);
            scheduleAndWalletService.save(userSaved.getWallet(), schedule);


            schedule = new Schedule();
            schedule.setName("salario");
            schedule.setPrice(1800.00);
            schedule.setDayToPay(5);
            schedule.setBillType(BillType.GANHO);
            scheduleAndWalletService.save(userSaved.getWallet(), schedule);



        };
    }


}
