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
import java.util.List;

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
            selectBill(userService, billAndWalletService);


            //remover uma conta

            //alterar uma conta






        };
    }

    private void selectBill(UserService userService, BillAndWalletService billAndWalletService) {
        Wallet wallet = generateAndCheckUser(userService);

        //por id
        Long billId = 1l;
        Bill bill = billAndWalletService.selectById(wallet, billId);
        System.out.println("por ID: " + bill);

        //por nome
        Bill churrascaria = billAndWalletService.selectByName(wallet, "churrascaria");
        System.out.println("por Name: " + churrascaria);

        //por data
        List<Bill> byPayday = billAndWalletService.selectByDate(wallet, LocalDate.now());
        byPayday.stream().map(s -> "por Payday: " + s).forEach(System.out::println);

        //por mes
        List<Bill> byMonth = billAndWalletService.selectByMonth(wallet, 3);
        byMonth.stream().map(s -> "por Month: " + s).forEach(System.out::println);

        //por ano
        List<Bill> byYear = billAndWalletService.selectByYear(wallet, 2018);
        byYear.stream().map(s -> "por Year: " + s).forEach(System.out::println);

        //por pagamento efetuado
        List<Bill> byPaidBill = billAndWalletService.findByPaidBill(wallet);
        byPaidBill.stream().map(s -> "por Paid Bill: " + s).forEach(System.out::println);

        //por pagamento a ser realizado
        List<Bill> byScheduleBill = billAndWalletService.findByScheduleBill(wallet);
        byScheduleBill.stream().map(s -> "por Schedule Bill: " + s).forEach(System.out::println);
    }

    private void salvarUmaConta(UserService userService, BillAndWalletService billAndWalletService) {

        User user = new User();

        //salvar conta extra
        Wallet wallet = generateAndCheckUser(userService);
        Bill bill = generateBill("churrascaria", 100.0, LocalDate.now(), true, BillType.GASTOS_NORMAL, 0);
        billAndWalletService.save(wallet, bill);


        // salvar conta tipo schedule
        wallet = generateAndCheckUser(userService);
        Bill schedule = generateBill("carro", 600.0, null, false, BillType.GASTOS_PLANEJADO, 10);
        billAndWalletService.save(wallet, schedule);


        wallet = generateAndCheckUser(userService);
        Bill earning = generateBill("salario", 1000.0, null, false, BillType.GANHO_PLANEJADO, 5);
        billAndWalletService.save(wallet, earning);


        wallet = generateAndCheckUser(userService);
        Bill bonus = generateBill("freela", 100.0, LocalDate.now(), true, BillType.GANHO_NORMAL, 0);
        billAndWalletService.save(wallet, bonus);


        wallet = generateAndCheckUser(userService);
        LocalDate date = LocalDate.now();
        date = date.minusMonths(3);
        bonus = generateBill("freela 2", 200.0, date, true, BillType.GANHO_NORMAL, 0);
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
