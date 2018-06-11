package com.dmedeiros.mybill.bill.resource;

import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.service.BillAndWalletService;
import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.service.UserService;
import com.dmedeiros.mybill.util.SecurityToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/bill")
public class BillController {

    @Autowired
    BillAndWalletService billAndWalletService;

    @Autowired
    UserService userService;


    @GetMapping
    public List<Bill> findAllBill(@RequestHeader String token) {
        User user = decodeToken(token, userService);
        return billAndWalletService.selectAll(user.getWallet());
    }

    @GetMapping("/{id}")
    public Bill findBillById(@PathVariable Long id, @RequestHeader String token) {
        User user = decodeToken(token, userService);
        return billAndWalletService.selectById(user.getWallet(), id);
    }

    @GetMapping("/month/{month}")
    public List<Bill> findBillByMonth(@PathVariable int month, @RequestHeader String token) {
        User user = decodeToken(token, userService);
        return billAndWalletService.selectByMonth(user.getWallet(), month);
    }

    @GetMapping("/year/{year}")
    public List<Bill> findBillByYear(@PathVariable int year, @RequestHeader String token) {
        User user = decodeToken(token, userService);
        return billAndWalletService.selectByYear(user.getWallet(), year);
    }

    @GetMapping("/payday/{payday}")
    public List<Bill> findBillByPayday(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)@PathVariable LocalDate payday
            , @RequestHeader String token) {
        User user = decodeToken(token, userService);
        return billAndWalletService.selectByDate(user.getWallet(), payday);
    }

    @PostMapping
    public ResponseEntity<?> saveBill(@Valid @RequestBody Bill bill, @RequestHeader String token) {
        User user = decodeToken(token, userService);

        return Optional.of(bill)
                .map(billToBeSaved -> {
                    Bill savedBill = billAndWalletService.save(user.getWallet(), billToBeSaved);
                    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedBill.getId())
                            .toUri();
                    return ResponseEntity.created(uri).build(); })
                .orElse(ResponseEntity.noContent().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestHeader String token){
        User user = decodeToken(token, userService);

        return Optional.of(id)
                .map(billToDelete -> {
                    billAndWalletService.remove(user.getWallet(), id);
                    return ResponseEntity.ok().build(); })
                .get();

    }


    private synchronized User decodeToken(String token, UserService userService) {
        Long userId = SecurityToken.getUserIdFromToken(token);
        User user = userService.findUserById(userId);
        return user;
    }

}

