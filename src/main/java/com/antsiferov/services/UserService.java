package com.antsiferov.services;

import com.antsiferov.Constants;
import com.antsiferov.entity.CustomUser;
import com.antsiferov.entity.User;
import com.antsiferov.interfaces.UsersInterface;
import com.antsiferov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements UsersInterface, ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                .map(user -> new CustomUser(user))
                .orElse(null);
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        updateLastLogin(username);
    }

    private void updateLastLogin(String username) {
        DateFormat df = new SimpleDateFormat(Constants.dateFormat);
        userRepository.setLastLogin(df.format(new Date()), username);
    }

    public Optional<User> findByName(String username) {
        return userRepository.findByName(username);
    }

    public User findUserByName(String username) {
        return userRepository.findUserByName(username);
    }

    public User findUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}