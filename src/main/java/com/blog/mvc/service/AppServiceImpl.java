package com.blog.mvc.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Locale;
import java.util.regex.Pattern;
import java.text.Normalizer;
import java.text.Normalizer.Form;

@Service
public class AppServiceImpl implements AppService {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public String removeQueryStringParameter(String url) {
        List<String> paramAsList = new ArrayList<>();
        String newQueryString;

        if (url != null) {
            int i = 0;
            String[] arrayUrl = url.split("&");
            for (String params : arrayUrl) {
                String pointer = arrayUrl[i].split("=")[0];
                if (!pointer.equals("page") && !pointer.equals("rel")) {
                    paramAsList.add(params);
                }
                i++;

            }

        }

        String addConcat = (paramAsList.size() > 0 && url != null) ? "?" : "";
        newQueryString = addConcat + StringUtils.collectionToDelimitedString(paramAsList, "&");

        return newQueryString;

    }

    public Pageable pageable(Integer page, Integer size) {
        page = page == null ? 0 : (page.intValue() - 1);
        return new PageRequest(page, size);
    }

    public String makeSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

}
