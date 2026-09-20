package com.example.restClient.controller;

import com.example.restClient.dto.PostDto;
import com.example.restClient.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private  final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public List<PostDto> getAllPost(){
        return postService.getAll();
    }
    @GetMapping("{id}")
    public  PostDto getPostById(@PathVariable Integer id){
        return postService.postById(id);
    }
    @PostMapping
    public PostDto create (@RequestBody PostDto postDto){
        return postService.create(postDto);
    }
    @PutMapping("/{id}")
    public PostDto update (@PathVariable Integer id,@RequestBody PostDto postDto){
        return postService.update(id,postDto);
    }
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Integer id){
       postService.delete(id);
    }

}
