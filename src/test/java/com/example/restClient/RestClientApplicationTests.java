package com.example.restClient;

import com.example.restClient.dto.PostDto;
import com.example.restClient.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import java.util.List;

@SpringBootTest
class RestClientApplicationTests {
	@Autowired
	private PostService postService;

	@Test
	void getaAll() {
		List<PostDto> dto=postService.getAll();
		System.out.println(dto);
	}
	@Test
	void getById(){
		PostDto dto=postService.postById(100);
		System.out.println(dto);
	}

	@Test
	void create(){
		PostDto body = new PostDto(1,"springboot","this is my project restclient");
//		System.out.println(body);
PostDto dto=postService.create(body);
System.out.println(dto);
	}
@Test
	void update(){
		PostDto body= new PostDto(2,"merstack","its update check");
		PostDto dto =postService.update(1,body);
	System.out.println(dto);
}
@Test
	void delete(){
		postService.delete(1);
	System.out.println("done");
}
}
