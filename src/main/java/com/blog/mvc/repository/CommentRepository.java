package com.blog.mvc.repository;

import com.blog.mvc.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by MOBIOT.
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}