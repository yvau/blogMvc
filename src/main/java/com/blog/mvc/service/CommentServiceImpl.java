package com.blog.mvc.service;

import com.blog.mvc.model.Comment;
import com.blog.mvc.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by MOBIOT.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    public void save(Comment comment){
        commentRepository.saveAndFlush(comment);
        return ;
    }

}











