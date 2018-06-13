package com.dmedeiros.mybill.user.controller;

import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 36000, exposedHeaders = "Location")
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

    @PutMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> autenticateUser(@RequestParam MultiValueMap formData) {

        String login = (String) formData.getFirst("login");
        String password = (String) formData.getFirst("password");

        return Optional.of(new User(password, login))
                .map(userToBeVerify -> {
                    String userReference = new UserResource(userService.autenticate(userToBeVerify))
                            .getLink("self")
                            .getHref();
                    return ResponseEntity.created(URI.create(userReference)).build(); })
                .orElse(ResponseEntity.noContent().build());
    }


}
