package com.example.restClient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Bean
    public RestClient restClient(){
        RestClient restClient=RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
        return restClient;
    }
}
