package com.example.todaylunch.service;

import com.example.todaylunch.dao.Category;
import com.example.todaylunch.dto.ResponseDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class ApiService {

    private final String apiKey;
    private final String apiUrl;
    private final String xAddress;
    private final String yAddress;

    public ApiService( @Value("${apiKey}")String apiKey, @Value("${apiUrl}")String apiUrl) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        this.xAddress = "127.1066904";
        this.yAddress = "37.4003183";
    }

    public ResponseDTO getRandomFromApi(Category category) throws Exception{
        Random ran=new Random();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        Map<String, String> param = new HashMap<>();
        if(category==Category.전체) {
            param.put("query", "맛집");
        }else{
            param.put("query", category.name());
        }
        param.put("query", category.name());
        param.put("x", xAddress);
        param.put("y", yAddress);
        param.put("radius", "5000");
        param.put("size", "1");
        param.put("sort", "distance");
        param.put("page", String.valueOf(ran.nextInt(45)+1));

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl);
        for (Map.Entry<String, String> entry : param.entrySet()) {
            uriBuilder.queryParam(entry.getKey(), entry.getValue());
        }
        URI uri = uriBuilder.build().encode().toUri();

        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                String.class
        );
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.getBody());
        JsonNode documents = root.path("documents");
        JsonNode firstDocument = documents.get(0);

        return ResponseDTO.builder()
                .id(firstDocument.path("id").asLong())
                .xAddress(firstDocument.path("x").asText())
                .yAddress(firstDocument.path("y").asText())
                .address(firstDocument.path("road_address_name").asText())
                .name(firstDocument.path("place_name").asText())
                .url(firstDocument.path("place_url").asText())
                .categoryName(firstDocument.path("category_name").asText())
                .distance(firstDocument.path("distance").asInt())
                .build();
    }

    /*
    public List<RequestDTO> getListFromApi(Category category) {

    }
     */
}
