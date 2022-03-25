package com.antsiferov.config;

import com.antsiferov.services.CustomOAuth2UserService;
import com.antsiferov.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersService usersService;

    @Autowired
    private CustomOAuth2UserService oAuth2UserService;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers("/register/**").not().fullyAuthenticated()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/**", "/home/**", "/feed/**", "/lk/**", "/find", "/login", "/oauth2/**", "/changelanguage").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
//                    .loginPage("/login")
                    .defaultSuccessUrl("/feed")
                    .permitAll()
                .and()
                    .oauth2Login()
//                    .loginPage("/login")
                    .defaultSuccessUrl("/feed")
                    .userInfoEndpoint().userService(oAuth2UserService)
                    .and()
                    .successHandler(oAuth2LoginSuccessHandler)
//                    .oauth2Login()
//                    .loginPage("/logingoogle")
//                    .defaultSuccessUrl("/feed")
//                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public PrincipalExtractor principalExtractor() {
//        return map -> {
//            String name = (String) map.get("name");
//            User user = usersRepository.findByName(name).orElseGet(()-> new User(name));
//            usersRepository.save(user);
//            return usersService.loadUserByUsername(name);
//        };
//    }


}