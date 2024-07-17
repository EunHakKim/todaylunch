package com.example.todaylunch.domain.kakaoMapApi.controller;

import com.example.todaylunch.domain.kakaoMapApi.entity.Category;
import com.example.todaylunch.domain.kakaoMapApi.dto.RequestDTO;
import com.example.todaylunch.domain.kakaoMapApi.dto.ResponseDTO;
import com.example.todaylunch.domain.kakaoMapApi.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/kakao/v1")
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/category")
    public ResponseEntity<ResponseDTO> getRandomFromApi(
            @RequestBody RequestDTO requestDTO
    ) throws Exception {
        return ResponseEntity.ok()
                .body(apiService.getRandomFromApi(requestDTO));
    }

    @GetMapping("/category/list/{category}")
    public ResponseEntity<List<ResponseDTO>> getListFromApi(
            @PathVariable("category")Category category
    ) throws Exception {
        return ResponseEntity.ok()
                .body(apiService.getListFromApi(category));
    }
}
