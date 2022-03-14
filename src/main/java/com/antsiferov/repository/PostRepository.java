package com.antsiferov.repository;

import com.antsiferov.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findAll();
    Post findPostById(Long id);
}