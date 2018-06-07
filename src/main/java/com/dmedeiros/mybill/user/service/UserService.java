package com.dmedeiros.mybill.user.service;

import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.user.exception.UserException;
import com.dmedeiros.mybill.user.exception.UserNotFoundException;
import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.repository.UserRepository;
import com.dmedeiros.mybill.util.SecurityAuxiliary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;




    public void prepareToAndSave(User user) {
        prepareUser(user, true);
        saveUser(user);
    }

    private void saveUser(User user) {
        userRepository.save(user);
    }

    private void prepareUser(User user, boolean isNew) {

        verifyIfIsValid(user, "usuario nao foi preenchido");
        passwordDigester(user);

        if (isNew) {
            user.setActive(true);
            Wallet wallet = new Wallet();
            wallet.setUser(user);
            user.setWallet(wallet);
        }
    }

    private void passwordDigester(User user){

        String password = user.getPassword();

        try {
            password = SecurityAuxiliary.digester(password);
            user.setPassword(password);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private boolean verify(User user) {
        User existingUser = verifyIfExists(user);
        boolean isValid = verifyIfIsValid(existingUser);
        return isValid;
    }

    public User verifyIfExists(User user) {
        prepareUser(user, false);
        Optional<User> userFound = userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());
        if (!userFound.isPresent())
            throw new UserNotFoundException(user.getLogin());

        return userFound.get();
    }



    private boolean verifyIfIsValid(User user, String message) {

        if (user.isEmpty(user))
            throw new UserException(message);

        return true;
    }

    private boolean verifyIfIsValid(User user) {

        if (user.isEmpty(user))
            throw new UserException("user is not valid");

        return true;
    }

    public void verifyAndRemove(User user) {
        if (verify(user))
            userRepository.delete(user);
    }

    public void verifyAndUpdate(User user) {
        if (verify(user))
            saveUser(user);
    }
}
