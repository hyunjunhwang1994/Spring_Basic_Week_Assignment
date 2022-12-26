package com.hyunjun.spring_basic_week_assignment.repository;

import com.hyunjun.spring_basic_week_assignment.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findAllByOrderByModifiedAtDesc();



}
