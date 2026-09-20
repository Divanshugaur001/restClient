package com.example.restClient.service;

import com.example.restClient.dto.PostDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.rmi.RemoteException;
import java.util.List;

@Service
public class PostService {
    private final RestClient restClient;

    Logger log= LoggerFactory.getLogger(PostService.class);

    public PostService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<PostDto> getAll(){
        log.trace("trying to retrieve all in getAll ");
        try {
//        log.error("error log");
//        log.warn("warn mesage");
//        log.info("info message");
//        log.debug("debug message");
//        log.trace("trace message");
            List<PostDto> res = restClient.get()
                    .uri("/posts")
                    .retrieve().
                    onStatus(HttpStatusCode::is4xxClientError,(req,resp)->{
                        log.error(new String(resp.getBody().readAllBytes()));
                        throw new ResourceAccessException("could not acess");
                    })
                    .body(new ParameterizedTypeReference<List<PostDto>>() {
                    });
            log.info("successfully getAll() reterived the data");
            log.trace("retrieved employees list in getAllDATA:{}",res);
            return res;
        }
        catch(Exception e){
            log.error("EXCEPTOION  ocuured in getAll employeee",e);
            throw  new RuntimeException();
        }
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
