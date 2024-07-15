package com.example.todaylunch.domain;

import lombok.Getter;

import java.util.Random;

@Getter
public enum Western {
    FASTFOOD("패스트푸드"),
    WESTERN("양식");

    private String koreanName;

    Western(String koreanName) {
        this.koreanName = koreanName;
    }

    private static final Random ran = new Random();

    public static Western getRandom()  {
        Western[] westerns = values();
        return westerns[ran.nextInt(westerns.length)];
    }
}
