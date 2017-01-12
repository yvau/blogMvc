package com.blog.mvc.service;

import com.blog.mvc.model.User;
import com.blog.mvc.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by MOBIOT.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public User find(int id){

        return userRepository.findOne(id);
    }

    public User findByUsername(String username){

        return userRepository.findByUsername(username);
    }

}











