package com.blog.mvc.service;

import com.blog.mvc.model.Comment;
import com.blog.mvc.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by MOBIOT.
 */
public interface CommentService {

    void save(Comment comment);

}
