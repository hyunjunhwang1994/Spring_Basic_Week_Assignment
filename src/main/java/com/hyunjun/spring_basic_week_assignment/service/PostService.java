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
    public PostResponseDto readPost(Long id) {

        //        SelectPostShowDto selectPostShowDto = new SelectPostShowDto();



        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("?????? ?????? ???????????? ????????????.")
        );
        //        selectPostShowDto.setId(post.getId());
        //        selectPostShowDto.setTitle(post.getTitle());
        //        selectPostShowDto.setAuthor(post.getAuthor());
        //        selectPostShowDto.setContents(post.getContents());



        ModelMapper modelMapper = new ModelMapper();
        PostResponseDto postResponseDto = modelMapper.map(post, PostResponseDto.class);



        return postResponseDto;
    }




    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {

        // Entity <- DTO
        // Post post = requestDto.toEntity();


        // ?????? ?????? ?????? ??????????????? ??????????????? ??????
        Post post = postRepository.findByIdAndPassword(id, requestDto.getPassword());


        // ???????????????
        if(post != null){
            // Dirty Checking??? ???????????? ????????????
            post.updatePost(requestDto);

            // DTO <- Entity
            PostResponseDto postResponseDto = new PostResponseDto(post);

            return postResponseDto;
        }else{
            return null;
        }
    }



}
