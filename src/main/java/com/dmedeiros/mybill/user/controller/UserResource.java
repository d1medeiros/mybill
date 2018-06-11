package com.dmedeiros.mybill.user.controller;

import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.util.SecurityToken;
import org.json.JSONObject;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


public class UserResource extends ResourceSupport {

    private final User user;

    public UserResource(User user) {

        this.user = user;

        String token = prepareToken(user);

        this.add(new Link(token).withSelfRel());
        this.add(linkTo(methodOn(UserController.class).saveUser(user)).withSelfRel());

    }


    public User getUser() {
        return user;
    }

    public String prepareToken(User user) {
        return SecurityToken.generateHash(user);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.putOpt("token", token);
//        return jsonObject.toString();
    }

}
