package com.antsiferov.entity;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
//@Table(name = "comments_table")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String date;

    @Column
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_name")
//    private String user;

    @Column
    private String author;

//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;

    public Comment(String text, Post post) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        this.author = auth.getName();
        this.date = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(new Date());
        this.text = text;
        this.post = post;
    }

    public Comment() {
    }
}