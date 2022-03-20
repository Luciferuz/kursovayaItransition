package com.antsiferov.repository;

import com.antsiferov.entity.Comment;
import com.antsiferov.entity.Post;
import com.antsiferov.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllCommentsByPostId(Long postId);

    List<Comment> findAllCommentsByUser(User user);

    Comment findCommentById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Comment comment SET comment.text = ?2 WHERE comment.id = ?1")
    void updateCommentText(Long id, String text);

    @Transactional
    @Query(value = "select * from comment c where text like %?1% or author like %?1%", nativeQuery = true)
    List<Comment> search(String text);

}