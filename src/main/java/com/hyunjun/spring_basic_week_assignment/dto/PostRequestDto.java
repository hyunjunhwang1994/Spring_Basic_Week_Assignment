package com.hyunjun.spring_basic_week_assignment.dto;

import com.hyunjun.spring_basic_week_assignment.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private String contents;
    private String author;

    private String password;


    @Builder
    public PostRequestDto(String title, String contents, String author, String password) {
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.password = password;
    }


    // dto -> entity
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .contents(contents)
                .author(author)
                .password(password)
                .build();
    }

}
