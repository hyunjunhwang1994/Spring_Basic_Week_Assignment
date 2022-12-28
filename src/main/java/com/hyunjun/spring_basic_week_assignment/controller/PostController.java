package com.hyunjun.spring_basic_week_assignment.controller;


import com.hyunjun.spring_basic_week_assignment.dto.PostDeleteResultDto;
import com.hyunjun.spring_basic_week_assignment.dto.PostRequestDto;
import com.hyunjun.spring_basic_week_assignment.dto.PostResponseDto;
import com.hyunjun.spring_basic_week_assignment.dto.SelectPostShowDto;
import com.hyunjun.spring_basic_week_assignment.entity.Post;
import com.hyunjun.spring_basic_week_assignment.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }


    @GetMapping("/upload/post")
    public ModelAndView uploadPost() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("uploadpost");
        return modelAndView;
    }

    // 글 입력
    @PostMapping("/api/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {


        return postService.createPost(requestDto);
    }

    // 전체 글 조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPosts() {



        return postService.getPosts();
    }


    // 선택 글 조회
    @GetMapping("/api/post/{id}")
    public ModelAndView readPost(@PathVariable Long id) {

        SelectPostShowDto selectPostShowDto = postService.readPost(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post");


        modelAndView.addObject(selectPostShowDto);

        return modelAndView;

    }

    // 선택 글 삭제 (비밀번호 매칭)
    @DeleteMapping("/api/post/{id}")
    public PostDeleteResultDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {


        boolean result = postService.deletePost(id, requestDto.getPassword());

        if(result == true){
            return new PostDeleteResultDto(true);
        }else{
            return new PostDeleteResultDto(false);
        }

    }


    // 선택 글 수정
    @PutMapping("/api/post/{id}")
    public PostResponseDto updatePost(@PathVariable long id, @RequestBody PostRequestDto requestDto) {

        PostResponseDto postResponseDto = postService.updatePost(id, requestDto);


        return postResponseDto;
    }







}
