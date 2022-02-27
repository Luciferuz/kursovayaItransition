package com.antsiferov.entity;

import lombok.Data;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "posts_table")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String date;

    @Column
    private String text;

    @Column
    private String subject;

    @Column
    private Integer likes;

    @Column
    private Integer dislikes;

    @Column
    private String pictureURL;

    public Post(String subject, String text){
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        this.date = df.format(new Date());
        this.text = text;
        this.subject = subject;
        this.likes = 0;
        this.dislikes = 0;
    }


    public Post() {

    }
}