package com.dmedeiros.mybill.user.repository;

import com.dmedeiros.mybill.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByLoginAndPassword(String login, String password);

}
