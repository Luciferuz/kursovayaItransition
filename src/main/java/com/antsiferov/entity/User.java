package com.antsiferov.entity;
import lombok.Data;
import javax.persistence.*;

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

}