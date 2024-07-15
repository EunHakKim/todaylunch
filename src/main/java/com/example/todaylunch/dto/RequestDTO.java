package com.example.todaylunch.dto;

import com.example.todaylunch.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private List<Category> category;
}
