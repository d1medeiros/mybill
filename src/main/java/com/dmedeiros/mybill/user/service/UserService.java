package com.dmedeiros.mybill.user.service;

import com.dmedeiros.mybill.bill.model.Wallet;
import com.dmedeiros.mybill.user.exception.UserException;
import com.dmedeiros.mybill.user.exception.UserNotFoundException;
import com.dmedeiros.mybill.user.model.User;
import com.dmedeiros.mybill.user.repository.UserRepository;
import com.dmedeiros.mybill.util.SecurityAuxiliary;
import com.dmedeiros.mybill.util.SecurityToken;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User findUserById(Long id){
        User user = userRepository.findById(id).get();
        if (user.isEmpty())
            throw new UserNotFoundException(id.toString());

        return user;
    }

    public User prepareToAndSave(User user) {
        prepareUser(user, true);
        return saveUser(user);
    }

    private User saveUser(User user) {
        return userRepository.save(user);
    }

    private void prepareUser(User user, boolean isNew) {

        verifyIfIsValid(user, "usuario nao foi preenchido");
        SecurityAuxiliary.passwordDigester(user);

        if (isNew) {
            user.setActive(true);
            Wallet wallet = new Wallet();
            wallet.setUser(user);
            user.setWallet(wallet);
        }
    }

    private boolean verify(User user) {
        User existingUser = verifyIfExists(user);
        boolean isValid = verifyIfIsValid(existingUser);
        return isValid;
    }

    public User autenticate(User user){
        User userVerified = verifyIfExists(user);
        changeLastAccess(userVerified);
        return userVerified;
    }

    public User verifyIfExists(User user) {
        prepareUser(user, false);
        Optional<User> userMaybeFound = userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());
        if (!userMaybeFound.isPresent())
            throw new UserNotFoundException(user.getLogin());

        User userFound = userMaybeFound.get();
        return userFound;
    }

    private void changeLastAccess(User user){
        user.setLastAccess(LocalDate.now());
    }

    private boolean verifyIfIsValid(User user, String message) throws UserException{

        if (user.isEmpty())
            throw new UserException(message);

        return true;
    }

    private boolean verifyIfIsValid(User user) {

        if (user.isEmpty())
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
