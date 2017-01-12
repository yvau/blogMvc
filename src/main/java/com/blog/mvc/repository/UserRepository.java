package com.blog.mvc.repository;

import com.blog.mvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by MOBIOT.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}