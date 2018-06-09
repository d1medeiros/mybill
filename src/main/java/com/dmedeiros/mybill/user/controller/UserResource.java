package com.dmedeiros.mybill.user.controller;

import com.dmedeiros.mybill.user.model.User;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


public class UserResource extends ResourceSupport {

    private final User user;

    public UserResource(User user) {

        this.user = user;

        String login = user.getLogin();

        this.add(linkTo(methodOn(UserController.class).saveUser(user)).withSelfRel());

    }


    public User getUser() {
        return user;
    }


}
