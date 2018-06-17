package com.dmedeiros.mybill.bill.resource;

import com.dmedeiros.mybill.bill.model.Schedule;
import com.dmedeiros.mybill.bill.service.ScheduleAndWalletService;
import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.service.UserService;
import com.dmedeiros.mybill.util.SecurityToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 36000, exposedHeaders = "Location")
@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

    @Autowired
    ScheduleAndWalletService scheduleAndWalletService;

    @Autowired
    UserService userService;

    @GetMapping
    public List<Schedule> findAllSchedule(@RequestHeader String token) {
        User user = decodeToken(token, userService);
        return scheduleAndWalletService.selectAll(user.getWallet());
    }

    @GetMapping("/{id}")
    public Schedule findScheduleById(@PathVariable Long id, @RequestHeader String token) {
        User user = decodeToken(token, userService);
        return scheduleAndWalletService.selectById(user.getWallet(), id);
    }

    @GetMapping("/day/{day}")
    public List<Schedule> findScheduleByDay(@PathVariable int day, @RequestHeader String token) {
        User user = decodeToken(token, userService);
        return scheduleAndWalletService.selectByDay(user.getWallet(), day);
    }

    @PostMapping
    public ResponseEntity<?> saveSchedule(@Valid @RequestBody Schedule schedule, @RequestHeader String token) {
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
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestHeader String token){
        User user = decodeToken(token, userService);

        return Optional.of(id)
                .map(scheduleToDelete -> {
                    scheduleAndWalletService.remove(user.getWallet(), id);
                    return ResponseEntity.ok().build(); })
                .get();

    }


    private synchronized User decodeToken(String token, UserService userService) {
        Long userId = SecurityToken.getUserIdFromToken(token);
        User user = userService.findUserById(userId);
        return user;
    }

}

