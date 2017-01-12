package com.blog.mvc.repository;

import com.blog.mvc.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by MOBIOT.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findBySlug(String slug);
}