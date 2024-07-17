package com.example.todaylunch.domain.kakaoMapApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String categoryName;
    private String url;
    private String xAddress;
    private String yAddress;
    private Integer distance;
}
