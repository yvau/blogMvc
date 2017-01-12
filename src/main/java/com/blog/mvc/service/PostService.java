package com.blog.mvc.service;

import com.blog.mvc.model.Category;
import com.blog.mvc.model.Post;
import com.blog.mvc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Created by MOBIOT.
 */
public interface PostService {

    Post findBySlug(String slug);

    Page<Post> findByCategory(Category category, Pageable pageable);

    Page<Post> findByUser(User user, Pageable pageable);

    void save(Post post);

    void delete(int id);

    Post find(int id);

    Page<Post> findAll(Pageable pageable);
    
}
