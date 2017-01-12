package com.blog.mvc.service;

import com.blog.mvc.model.Category;
import com.blog.mvc.model.Post;
import com.blog.mvc.model.User;
import com.blog.mvc.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by MOBIOT.
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    public void save(Post post){

        postRepository.save(post);
        return;
    }

    public void delete(int id){

        postRepository.delete(id);
        return;
    }

    public Post findBySlug(String slug){
        return postRepository.findBySlug(slug);
    }

    public Post find(int id){

        return postRepository.findOne(id);
    }

    public Page<Post> findByCategory(Category category, Pageable pageable){

        return postRepository.findByCategory(category, pageable);
    }

    public Page<Post> findByUser(User user, Pageable pageable){

        return postRepository.findByUser(user, pageable);
    }

    public Page<Post> findAll(Pageable pageable){

        return postRepository.findAll(pageable);
    }

}











