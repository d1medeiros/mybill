package com.dmedeiros.mybill.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(method = RequestMethod.GET, path = "/user/teste")
    public String teste() {
        return "teste";
    }

}
