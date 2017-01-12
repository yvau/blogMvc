package com.blog.mvc.repository;

import com.blog.mvc.model.Category;
import com.blog.mvc.model.Post;
import com.blog.mvc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by MOBIOT.
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findBySlug(String slug);

    Page<Post> findByCategory(Category category, Pageable pageable);

    Page<Post> findByUser(User user, Pageable pageable);
}