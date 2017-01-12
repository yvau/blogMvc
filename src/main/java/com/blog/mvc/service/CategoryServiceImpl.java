package com.blog.mvc.service;

import com.blog.mvc.model.Category;
import com.blog.mvc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by MOBIOT.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category findBySlug(String slug){

        return categoryRepository.findBySlug(slug);
    }

    public List<Category> findAll(){

        return categoryRepository.findAll();
    }

}











