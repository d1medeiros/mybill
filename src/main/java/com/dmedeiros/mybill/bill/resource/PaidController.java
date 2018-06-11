package com.dmedeiros.mybill.bill.resource;

import com.dmedeiros.mybill.bill.model.Paid;
import com.dmedeiros.mybill.bill.service.PaidAndWalletService;
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
@RequestMapping(value = "/paid")
public class PaidController {

    @Autowired
    PaidAndWalletService paidAndWalletService;

    @Autowired
    UserService userService;


    @GetMapping
    public List<Paid> findAllBill(@RequestHeader String token) {
        User user = decodeToken(token, userService);
        return paidAndWalletService.selectAll(user.getWallet());
    }

    @GetMapping("/{id}")
    public Paid findBillById(@PathVariable Long id, @RequestHeader String token) {
        User user = decodeToken(token, userService);
        return paidAndWalletService.selectById(user.getWallet(), id);
    }

    @GetMapping("/month/{month}")
    public List<Paid> findBillByMonth(@PathVariable int month, @RequestHeader String token) {
        User user = decodeToken(token, userService);
        return paidAndWalletService.selectByMonth(user.getWallet(), month);
    }

    @GetMapping("/year/{year}")
    public List<Paid> findBillByYear(@PathVariable int year, @RequestHeader String token) {
        User user = decodeToken(token, userService);
        return paidAndWalletService.selectByYear(user.getWallet(), year);
    }

    @GetMapping("/payday/{payday}")
    public List<Paid> findBillByPayday(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)@PathVariable LocalDate payday
            , @RequestHeader String token) {
        User user = decodeToken(token, userService);
        return paidAndWalletService.selectByDate(user.getWallet(), payday);
    }

    @PostMapping
    public ResponseEntity<?> saveBill(@Valid @RequestBody Paid paid, @RequestHeader String token) {
        User user = decodeToken(token, userService);

        return Optional.of(paid)
                .map(billToBeSaved -> {
                    Paid savedPaid = paidAndWalletService.save(user.getWallet(), billToBeSaved);
                    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedPaid.getId())
                            .toUri();
                    return ResponseEntity.created(uri).build(); })
                .orElse(ResponseEntity.noContent().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestHeader String token){
        User user = decodeToken(token, userService);

        return Optional.of(id)
                .map(billToDelete -> {
                    paidAndWalletService.remove(user.getWallet(), id);
                    return ResponseEntity.ok().build(); })
                .get();

    }


    private synchronized User decodeToken(String token, UserService userService) {
        Long userId = SecurityToken.getUserIdFromToken(token);
        User user = userService.findUserById(userId);
        return user;
    }

}

