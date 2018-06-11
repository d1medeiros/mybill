package com.dmedeiros.mybill.bill.resource;

import com.dmedeiros.mybill.bill.model.Paid;
import com.dmedeiros.mybill.bill.model.Schedule;
import com.dmedeiros.mybill.bill.service.PaidAndWalletService;
import com.dmedeiros.mybill.bill.service.ScheduleAndWalletService;
import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.service.UserService;
import com.dmedeiros.mybill.util.SecurityToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/wallet")
public class WalletController {

    @Autowired
    private ScheduleAndWalletService scheduleAndWalletService;
    @Autowired
    private PaidAndWalletService paidAndWalletService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> paySchedule(@RequestParam Long id,
                                         @RequestHeader String token) {
        User user = decodeToken(token, userService);

        Schedule schedule = scheduleAndWalletService.selectById(user.getWallet(), id);
        Paid paid = paidAndWalletService.generatePaidFromSchedule(schedule);

        return Optional.of(paid)
                .map(paidToBeSaved -> {
                    Paid savedPaid = paidAndWalletService.save(user.getWallet(), paidToBeSaved);
                    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedPaid.getId())
                            .toUri();
                    return ResponseEntity.created(uri).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }


    private synchronized User decodeToken(String token, UserService userService) {
        Long userId = SecurityToken.getUserIdFromToken(token);
        User user = userService.findUserById(userId);
        return user;
    }

}
