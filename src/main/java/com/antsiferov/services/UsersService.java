package com.antsiferov.services;

import com.antsiferov.Constants;
import com.antsiferov.entity.CustomUser;
import com.antsiferov.interfaces.UsersInterface;
import com.antsiferov.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UsersService implements UsersInterface, ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByName(username)
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
        usersRepository.setLastLogin(df.format(new Date()), username);
    }
}
