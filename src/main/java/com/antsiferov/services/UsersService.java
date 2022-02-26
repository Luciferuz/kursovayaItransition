package com.antsiferov.services;

import com.antsiferov.entity.CustomUser;
import com.antsiferov.interfaces.UsersInterface;
import com.antsiferov.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UsersInterface {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByName(username)
                .map(user -> new CustomUser(user))
                .orElse(null);
    }

}
