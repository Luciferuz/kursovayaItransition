package com.antsiferov.repository;

import com.antsiferov.entity.Comment;
import com.antsiferov.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllCommentsByPostId(Long postId);

    List<Comment> findAllCommentsByUser(User user);
}
