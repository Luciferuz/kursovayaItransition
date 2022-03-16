package com.antsiferov.entity;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String date;

    @Column
    private String text;

    @Column
    private String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User user;


    public Comment(String text, Post post, User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        this.author = auth.getName();
        this.date = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(new Date());
        this.text = text;
        this.post = post;
        this.user = user;
    }

    public Comment() {
    }
}