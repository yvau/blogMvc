package com.blog.mvc.validators;

import com.blog.mvc.model.Post;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PostValidator implements Validator {

    @Override
    public boolean supports(Class<?> c) {
        //just validate the profiler instances
        return Post.class.isAssignableFrom(c);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Post post = (Post) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                "label.required.is");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content",
                "label.required.is");

    }

}