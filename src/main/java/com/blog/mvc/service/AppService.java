package com.blog.mvc.service;

import org.springframework.data.domain.Pageable;


public interface AppService {
	
	String removeQueryStringParameter(String url);
	
	Pageable pageable(Integer page, Integer size);

	String makeSlug(String input);

}
