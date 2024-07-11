package com.example.todaylunch.controller;

import com.example.todaylunch.dao.Category;
import com.example.todaylunch.dto.ResponseDTO;
import com.example.todaylunch.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/{category}")
    public ResponseEntity<ResponseDTO> getRandomFromApi(@PathVariable("category")Category category) throws Exception {
        return ResponseEntity.ok()
                .body(apiService.getRandomFromApi(category));
    }

    /*
    @GetMapping("/")
    public ResponseEntity<List<RequestDTO>> getRandomListFromApi(
            @PathVariable()Category category
    ){
        return ResponseEntity.ok()
                .body(apiService.getListFromApi(category));
    }

     */
}
