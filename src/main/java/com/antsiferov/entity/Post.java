package com.antsiferov.entity;

import com.antsiferov.Constants;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
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

    @Column
    private String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User user;

    public Post(String subject, String text, User user, String pictureURL) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        this.author = auth.getName();
        this.date = new SimpleDateFormat(Constants.dateFormat).format(new Date());
        this.text = text;
        this.subject = subject;
        this.likes = 0;
        this.dislikes = 0;
        this.user = user;
        this.pictureURL = pictureURL;
    }

    public Post() {

    }

    public String[] getPictureURL() {
        return pictureURL.split("\\s");
    }
}