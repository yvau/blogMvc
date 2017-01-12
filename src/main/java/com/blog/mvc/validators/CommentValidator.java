package com.blog.mvc.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blog.mvc.model.Comment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CommentValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public boolean supports(Class<?> c) {
        //just validate the profiler instances
        return Comment.class.isAssignableFrom(c);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Comment comment = (Comment) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content",
                "label.required.is");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
                "label.required.is");


        String email = comment.getMail();

        // email validation in spring
        if (!(email.isEmpty())) {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                errors.rejectValue("email", "label.email.incorrect");
            }
        }

    }

}