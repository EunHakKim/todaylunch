package com.example.todaylunch.converter;

import com.example.todaylunch.domain.Category;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Category> {
    @Override
    public Category convert(String source) {
        return Category.valueOf(source.toUpperCase());
    }
}
