package com.antsiferov.repository;

import com.antsiferov.entity.Post;
import com.antsiferov.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findAll();

    List<Post> findAllPostsByUser(User user);

    Post findPostById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Post post SET post.subject = ?2, post.text = ?3, post.pictureURL = ?4 WHERE post.id = ?1")
    void updatePost(Long id, String subject, String text, String pictureURL);

}