package com.dmedeiros.mybill.bill.resource;

import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.service.BillAndWalletService;
import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping(value = "/bill")
public class BillController {

    @Autowired
    BillAndWalletService billAndWalletService;

    @Autowired
    UserService userService;


    @GetMapping("/{id}")
    public Bill findBillById(@PathVariable Long id, @RequestParam String token) {
        User user = decodeToken(token, userService);
        return billAndWalletService.selectById(user.getWallet(), id);
    }

    @PostMapping
    public ResponseEntity<?> saveBill(@Valid @RequestBody Bill bill, @RequestParam String token) {
        User user = decodeToken(token, userService);

        return Optional.of(bill)
                .map(billToBeSaved -> {
                    Bill savedBill = billAndWalletService.save(user.getWallet(), bill);
                    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedBill.getId())
                            .toUri();
                    return ResponseEntity.created(uri).build(); })
                .orElse(ResponseEntity.noContent().build());

    }


    @Deprecated
    private User decodeToken(String token, UserService userService) {
        return userService.findUserById(1l);
    }

}

