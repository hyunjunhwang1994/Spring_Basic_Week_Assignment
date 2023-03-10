package com.hyunjun.spring_basic_week_assignment.entity;


import com.hyunjun.spring_basic_week_assignment.dto.PostRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;


    @Column(length = 50000, nullable = false)
    private String contents;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;


    @Builder
    public Post(Long id, String title, String contents, String author, String password) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.password = password;
    }

    public Post(PostRequestDto requestDto) {

        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
    }

    public void updatePost(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.author = requestDto.getAuthor();
    }


}
