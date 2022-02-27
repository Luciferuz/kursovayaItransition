package com.antsiferov.repository;

import com.antsiferov.entity.Post;
import com.antsiferov.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {


}
