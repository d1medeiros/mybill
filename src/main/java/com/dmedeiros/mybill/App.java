package com.dmedeiros.mybill;

import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.model.BillType;
import com.dmedeiros.mybill.bill.model.Wallet;
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
            saveUser(userService, user);


            //salvar uma conta
            salvarUmaConta(userService, billAndWalletService);

            //ver uma conta
//            selectBill(userService, billAndWalletService);


            //remover uma conta

            //alterar uma conta






        };
    }

    private void selectBill(UserService userService, BillAndWalletService billAndWalletService) {
        User user = new User();
//        user = generateAndCheckUser(user, userService);

        //por id
        int billId = 1;
        billAndWalletService.selectById(user, billId);

        //por nome
        //por valor
        //por data
        //por mes
        //por ano
        //por pagamento efetuado
        //por pagamento a ser realizado
    }

    private void salvarUmaConta(UserService userService, BillAndWalletService billAndWalletService) {

        User user = new User();

        //salvar conta extra
        Wallet wallet = generateAndCheckUser(userService);
        Bill bill = generateBill("churrascario", 100.0, LocalDate.now(), false, BillType.GASTOS_NORMAL, 0);
        billAndWalletService.save(wallet, bill);


        // salvar conta tipo schedule
        wallet = generateAndCheckUser(userService);
        Bill schedule = generateBill("carro", 600.0, null, true, BillType.GASTOS_PLANEJADO, 10);
        billAndWalletService.save(wallet, schedule);


        wallet = generateAndCheckUser(userService);
        Bill earning = generateBill("salario", 1000.0, null, false, BillType.GANHO_PLANEJADO, 5);
        billAndWalletService.save(wallet, earning);


        wallet = generateAndCheckUser(userService);
        Bill bonus = generateBill("freela", 100.0, LocalDate.now(), true, BillType.GANHO_NORMAL, 0);
        billAndWalletService.save(wallet, bonus);

    }

    private Bill generateBill(String name, double price, LocalDate payday, boolean paid, BillType billType, int dayToPay) {
        Bill bill = new Bill();
        bill.setName(name);
        bill.setPrice(price);
        bill.setPayday(payday);
        bill.setPaid(paid);
        bill.setBillType(billType);
        bill.setDayToPay(dayToPay);
        return bill;
    }

    private Wallet generateAndCheckUser(UserService userService) {

        User user = new User();
        user.setName("diego medeiros");
        user.setLogin("aian");
        user.setPassword("1234");
        user = userService.verifyIfExists(user);
        return user.getWallet();
    }

    private void saveUser(UserService userService, User user) {
        //save
        user.setName("diego medeiros");
        user.setLogin("aian");
        user.setPassword("1234");
        userService.prepareToAndSave(user);
    }

    private void userCRUD(User user, UserService userService) {


        //save
        userService.prepareToAndSave(user);


        //delete
//            user.setLogin("xx");
            userService.verifyAndRemove(user);

        //alter
//            user.setLogin("xx");
//        System.out.println("last access: " + user.getLastAccess());
        user.setLastAccess(LocalDate.now());
        userService.verifyAndUpdate(user);
//        System.out.println("last access: " + user.getLastAccess());
    }

}
