package com.antsiferov.repository;

import com.antsiferov.entity.Post;
import com.antsiferov.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findAll();

    List<Post> findAllPostsByUser(User user);

    Post findPostById(Long id);

}