package com.dmedeiros.mybill.bill.resource;

import com.dmedeiros.mybill.bill.model.Bill;
import com.dmedeiros.mybill.bill.model.Schedule;
import com.dmedeiros.mybill.bill.service.ScheduleAndWalletService;
import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.service.UserService;
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
@RequestMapping(value = "/schedule")
public class ScheduleController {

    @Autowired
    ScheduleAndWalletService scheduleAndWalletService;

    @Autowired
    UserService userService;


    @GetMapping("/{id}")
    public Schedule findScheduleById(@PathVariable Long id, @RequestParam String token) {
        User user = decodeToken(token, userService);
        return scheduleAndWalletService.selectById(user.getWallet(), id);
    }

    @GetMapping("/day/{day}")
    public List<Schedule> findScheduleByDay(@PathVariable int day, @RequestParam String token) {
        User user = decodeToken(token, userService);
        return scheduleAndWalletService.selectByDay(user.getWallet(), day);
    }

    @PostMapping
    public ResponseEntity<?> saveSchedule(@Valid @RequestBody Schedule schedule, @RequestParam String token) {
        User user = decodeToken(token, userService);

        return Optional.of(schedule)
                .map(scheduleToBeSaved -> {
                    Schedule savedSchedule = scheduleAndWalletService.save(user.getWallet(), scheduleToBeSaved);
                    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedSchedule.getId())
                            .toUri();
                    return ResponseEntity.created(uri).build(); })
                .orElse(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestParam String token){
        User user = decodeToken(token, userService);

        return Optional.of(id)
                .map(scheduleToDelete -> {
                    scheduleAndWalletService.remove(user.getWallet(), id);
                    return ResponseEntity.ok().build(); })
                .get();

    }

    @Deprecated
    private User decodeToken(String token, UserService userService) {
        return userService.findUserById(1l);
    }

}

