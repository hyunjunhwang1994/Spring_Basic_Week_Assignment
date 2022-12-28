package com.hyunjun.spring_basic_week_assignment.service;


import com.hyunjun.spring_basic_week_assignment.dto.PostRequestDto;
import com.hyunjun.spring_basic_week_assignment.dto.PostResponseDto;
import com.hyunjun.spring_basic_week_assignment.dto.SelectPostShowDto;
import com.hyunjun.spring_basic_week_assignment.entity.Post;
import com.hyunjun.spring_basic_week_assignment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto) {

        Post post = new Post(requestDto);
        postRepository.save(post);

        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }


    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {

        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();

        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        for (Post post : postList) {
            PostResponseDto postResponseDto = new PostResponseDto(post);
            postResponseDtoList.add(postResponseDto);

        }


        return postResponseDtoList;

    }

    @Transactional
    public boolean deletePost(Long id, String password) {


        Post post = postRepository.findByIdAndPassword(id ,password);


        if(post != null){

            postRepository.deleteById(id);
            return true;
        }

        return false;




    }

    @Transactional(readOnly = true)
    public SelectPostShowDto readPost(Long id) {

        //        SelectPostShowDto selectPostShowDto = new SelectPostShowDto();



        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 글이 존재하지 않습니다.")
        );
        //        selectPostShowDto.setId(post.getId());
        //        selectPostShowDto.setTitle(post.getTitle());
        //        selectPostShowDto.setAuthor(post.getAuthor());
        //        selectPostShowDto.setContents(post.getContents());


        ModelMapper modelMapper = new ModelMapper();
        SelectPostShowDto selectPostShowDto = modelMapper.map(post, SelectPostShowDto.class);


        return selectPostShowDto;
    }




    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {

        // Entity <- DTO
        // Post post = requestDto.toEntity();


        // 해당 글과 글과 비밀번호가 매칭되는지 확인
        Post post = postRepository.findByIdAndPassword(id, requestDto.getPassword());


        // 매칭된다면
        if(post != null){
            // Dirty Checking을 이용하여 업데이트
            post.updatePost(requestDto);

            // DTO <- Entity
            PostResponseDto postResponseDto = new PostResponseDto(post);

            return postResponseDto;
        }else{
            return null;
        }
    }



}
