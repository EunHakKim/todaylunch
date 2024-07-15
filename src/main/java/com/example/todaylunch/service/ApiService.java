package com.example.todaylunch.service;

import com.example.todaylunch.domain.Category;
import com.example.todaylunch.domain.Others;
import com.example.todaylunch.domain.Western;
import com.example.todaylunch.dto.RequestDTO;
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
import java.util.*;

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

    public ResponseDTO getRandomFromApi(RequestDTO requestDTO) throws Exception{
        Random ran=new Random();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "KakaoAK " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        Category category = requestDTO.getCategory().get(ran.nextInt(requestDTO.getCategory().size()));
        Map<String, String> param = setParam(category, 1, ran.nextInt(45)+1);

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

    public List<ResponseDTO> getListFromApi(Category category) throws Exception {
        List<ResponseDTO> responseList = new ArrayList<>();
        for(int i=0;i<2;i++){
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "KakaoAK " + apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            Map<String, String> param = setParam(category, 15, i+1);

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
            Iterator<JsonNode> elements = documents.elements();
            while (elements.hasNext()) {
                JsonNode document = elements.next();
                ResponseDTO responseDTO = ResponseDTO.builder()
                        .id(document.path("id").asLong())
                        .xAddress(document.path("x").asText())
                        .yAddress(document.path("y").asText())
                        .address(document.path("road_address_name").asText())
                        .name(document.path("place_name").asText())
                        .url(document.path("place_url").asText())
                        .categoryName(document.path("category_name").asText())
                        .distance(document.path("distance").asInt())
                        .build();

                responseList.add(responseDTO);
            }
        }
        return responseList;
    }

    private Map<String, String> setParam(Category category, int size, int page) {
        Map<String, String> param = new HashMap<>();
        if(category ==Category.ALL) {
            param.put("query", "맛집");
        }else if(category ==Category.OTHERS) {
            Others others = Others.getRandom();
            param.put("query", others.getKoreanName());
        } else if(category ==Category.WESTERN) {
            Western western = Western.getRandom();
            param.put("query", western.getKoreanName());
        } else {
            param.put("query", category.getKoreanName());
        }
        param.put("x", xAddress);
        param.put("y", yAddress);
        param.put("radius", "5000");
        param.put("size", String.valueOf(size));
        param.put("sort", "distance");
        param.put("page", String.valueOf(page));
        return param;
    }
}
