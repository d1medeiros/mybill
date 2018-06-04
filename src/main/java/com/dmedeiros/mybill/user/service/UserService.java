package com.dmedeiros.mybill.user.service;

import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.user.exception.UserException;
import com.dmedeiros.mybill.user.exception.UserNotFoundException;
import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;




    public void prepareToAndSave(User user) {
        prepareUser(user);
        saveUser(user);
    }

    private void saveUser(User user) {
        userRepository.save(user);
    }

    private void prepareUser(User user) {

        if (user.isEmpty(user))
            throw new UserException("usuario nao foi preenchido");

        user.setActive(true);
        user.setWallet(new Wallet());
    }

    public boolean verifyIfExists(User user) {

        if (user.isEmpty(user))
            throw new UserException("usuario esta vazio");

        Optional<User> userFound = userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());
        if (!userFound.isPresent())
            throw new UserNotFoundException(user.getLogin());

        return true;
    }

    public void verifyAndRemove(User user) {
        if (verifyIfExists(user))
            userRepository.delete(user);
    }

    public void verifyAndUpdate(User user) {
        if (verifyIfExists(user))
            saveUser(user);
    }
}
