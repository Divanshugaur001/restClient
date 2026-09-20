package com.example.restClient.service;

import com.example.restClient.dto.PostDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.rmi.RemoteException;
import java.util.List;

@Service
public class PostService {
    private final RestClient restClient;

    public PostService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<PostDto> getAll(){
        List<PostDto> res=restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<PostDto>>(){
                });


        return res;
    }

    public PostDto postById(Integer id){
        PostDto dto=restClient.get()
                .uri("/posts/{id}",id)
                .retrieve()
                .body(PostDto.class);
        return  dto;
    }

    public PostDto create(PostDto postDto){
        PostDto dto=restClient.post()
                .uri("/posts")
                .body(postDto)
                .retrieve()
                .body(PostDto.class);
        return dto;
    }
    public PostDto update(Integer id,PostDto postDto){
        return restClient.put()
                .uri("/posts/{id}",id)
                .body(postDto)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,(req,res)->{
                            throw new RemoteException("PAGE NOT FOUND OR CLIENT ERROR");
                        }
                )
                .onStatus(HttpStatusCode::is5xxServerError,(req,res)->{
                    throw  new RuntimeException("external server error");
                })
                .body(PostDto.class);
    }

    public void delete(Integer id){
         restClient.delete()
                .uri("/posts/{id}",id)
                .retrieve()
                .toBodilessEntity();

    }
}
