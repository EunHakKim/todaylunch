package com.example.todaylunch.domain;

import lombok.Getter;

@Getter
public enum Category {
    ALL("전체"),
    KOREAN("한식"),
    CHINESE("중식"),
    JAPANESE("일식"),
    WESTERN("양식"),
    OTHERS("기타");

    private String koreanName;

    Category(String koreanName) {
        this.koreanName = koreanName;
    }
}
