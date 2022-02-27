package com.antsiferov.repository;

import com.antsiferov.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String name);

}