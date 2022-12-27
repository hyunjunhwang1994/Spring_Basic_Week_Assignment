package com.hyunjun.spring_basic_week_assignment.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {

    private String title;
    private String contents;
    private String author;

    private String password;


}
