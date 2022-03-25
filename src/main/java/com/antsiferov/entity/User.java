package com.antsiferov.entity;

import com.antsiferov.Constants;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "users_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String role;

    @Column
    private String date;

    @Column
    private String lastLogin;

    @Column
    private String source;

    public User(String name, String password) {
        this.date = new SimpleDateFormat(Constants.dateFormat).format(new Date());
        this.name = name;
        this.password = password;
        this.role = "ROLE_USER";
        this.source = "Default";
    }

    public User() {
    }

    public User(String name){
        this.date = new SimpleDateFormat(Constants.dateFormat).format(new Date());
        this.name = name;
        this.role = "ROLE_USER";
        this.source = "Google";
    }

}