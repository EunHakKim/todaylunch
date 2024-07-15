package com.example.todaylunch.domain;

import lombok.Getter;

import java.util.Random;

@Getter
public enum Others {
    VIETNAMESE("베트남음식"),
    SOUTHEASTASIAN("동남아음식"),
    THAI("태국음식");

    private String koreanName;

    Others(String koreanName) {
        this.koreanName = koreanName;
    }

    private static final Random ran = new Random();

    public static Others getRandom()  {
        Others[] others = values();
        return others[ran.nextInt(others.length)];
    }
}
