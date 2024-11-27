package org.example.spring_study_9oormthon.web.dto;

import org.example.spring_study_9oormthon.domain.posts.Posts;

import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        
    }
}
