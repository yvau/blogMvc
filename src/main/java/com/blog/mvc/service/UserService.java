package com.blog.mvc.service;

import com.blog.mvc.model.User;

/**
 * Created by MOBIOT.
 */
public interface UserService {

    User find(int id);

    User findByUsername(String username);
    
}
