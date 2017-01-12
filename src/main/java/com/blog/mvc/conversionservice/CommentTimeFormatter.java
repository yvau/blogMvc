package com.blog.mvc.conversionservice;

import java.sql.Timestamp;
import com.ocpsoft.pretty.time.PrettyTime;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Yvau on 1/5/2017.
 */
public class CommentTimeFormatter implements Converter<Timestamp, String> {

    @Override
    public String convert(Timestamp timestamp) {
        PrettyTime p = new PrettyTime();
        p.format(timestamp);

        return p.format(timestamp);
    }

}