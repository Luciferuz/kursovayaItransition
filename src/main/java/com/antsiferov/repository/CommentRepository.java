package com.antsiferov.repository;

import com.antsiferov.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllCommentsByPostId(Long postId);
}
