package com.blog.mvc.conversionservice;

import com.github.rjeschke.txtmark.Processor;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Yvau on 1/5/2017.
 */
public class MarkdownFormatter implements Converter<String, String> {

    @Override
    public String convert(String text) {

        String result = Processor.process(text);

        return result.toString();
    }

}