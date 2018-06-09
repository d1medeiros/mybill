package com.dmedeiros.mybill.user.controller;

import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return Optional.of(user)
                .map(userToBeSaved -> {
                    User savedUser = userService.prepareToAndSave(userToBeSaved);
                    String userReference = new UserResource(savedUser).getLink("self").getHref();
                    return ResponseEntity.created(URI.create(userReference)).build(); })
                .orElse(ResponseEntity.noContent().build());

    }

}
