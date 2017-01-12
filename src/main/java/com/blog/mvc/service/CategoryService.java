package com.blog.mvc.service;

import com.blog.mvc.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by MOBIOT.
 */
public interface CategoryService {

    Category findBySlug(String slug);

    List<Category> findAll();
}
