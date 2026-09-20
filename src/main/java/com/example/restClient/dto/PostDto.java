package com.example.restClient.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class PostDto {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    public PostDto(Integer userId,String title,String body){
        this.userId=userId;
        this.title=title;
        this.body=body;

    }
}
