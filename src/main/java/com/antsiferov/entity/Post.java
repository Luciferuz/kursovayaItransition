package com.antsiferov.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "blogs_table")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
