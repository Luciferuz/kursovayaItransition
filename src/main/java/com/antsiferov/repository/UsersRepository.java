package com.antsiferov.repository;

import com.antsiferov.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {

    Optional<User> findByName(String name);

    User findUserById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users_table u SET u.last_login = ?1 WHERE u.name = ?2", nativeQuery = true)
    void setLastLogin(String lastLogin, String username);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users_table WHERE id = ?1", nativeQuery = true)
    void deleteUserById(Long id);
}