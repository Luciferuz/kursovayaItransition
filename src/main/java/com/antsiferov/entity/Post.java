package com.antsiferov.entity;

import com.antsiferov.Constants;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public Post(String subject, String text, User user, MultipartFile[] pictures) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        this.author = auth.getName();
        this.date = new SimpleDateFormat(Constants.dateFormat).format(new Date());
        this.text = text;
        this.subject = subject;
        this.likes = 0;
        this.dislikes = 0;
        this.user = user;
        this.pictureURL = pictures[0].isEmpty() ? Constants.defaultImg : separateURLs(correctURLs(pictures));
    }

    public Post() {

    }

    private ArrayList<String> correctURLs(MultipartFile[] pictures) {
        ArrayList<String> URLs = new ArrayList<>();
        for (MultipartFile picture : pictures) {
            String pictureName = picture.getOriginalFilename();
            //потом перенести в .yml
            String mainURLPart = "https://antsiferov.s3.eu-west-2.amazonaws.com/";
            String URL = mainURLPart + pictureName.replace(" ", "+");
            URLs.add(URL);
        }
        return URLs;
    }

    private String separateURLs(ArrayList<String> URLs) {
        StringBuilder separatedFormatURL = new StringBuilder();
        for (String url : URLs) {
            separatedFormatURL.append(url);
            separatedFormatURL.append(" ");
        }
        return separatedFormatURL.toString();
    }

    public String[] getPictureURL() {
        return pictureURL.split("\\s");
    }
}