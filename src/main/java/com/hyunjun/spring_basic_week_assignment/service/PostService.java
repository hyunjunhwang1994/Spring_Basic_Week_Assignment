package com.hyunjun.spring_basic_week_assignment.service;


import com.hyunjun.spring_basic_week_assignment.dto.PostRequestDto;
import com.hyunjun.spring_basic_week_assignment.entity.Post;
import com.hyunjun.spring_basic_week_assignment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    @Transactional
    public Post createPost(PostRequestDto requestDto) {

        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }


    @Transactional(readOnly = true)
    public List<Post> getPosts() {

        return postRepository.findAllByOrderByModifiedAtDesc();

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
    public Post readPost(Long id) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 글이 존재하지 않습니다.")
        );



        return post;
    }




    @Transactional
    public Post updatePost(Long id, PostRequestDto requestDto) {


        // 해당 글과 글과 비밀번호가 매칭되는지 확인
        Post post = postRepository.findByIdAndPassword(id, requestDto.getPassword());

        // 매칭된다면
        if(post != null){

            post.updatePost(requestDto);

            return post;
        }else{

            return null;
        }




    }



}
