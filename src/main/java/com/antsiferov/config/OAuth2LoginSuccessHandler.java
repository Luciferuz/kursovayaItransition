package com.antsiferov.config;

import com.antsiferov.entity.CustomOAuth2User;
import com.antsiferov.entity.User;
import com.antsiferov.repository.UsersRepository;
import com.antsiferov.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersService usersService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String username = oAuth2User.getName();
        if (!usersService.findUserByName(username).isPresent()) {
            usersRepository.save(new User(username));
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}