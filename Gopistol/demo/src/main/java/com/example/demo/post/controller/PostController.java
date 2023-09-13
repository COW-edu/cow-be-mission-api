package com.example.demo.post.controller;

import com.example.demo.post.controller.request.CreatePostRequest;
//import com.example.demo.post.controller.response.AllPostsResponseDTO;
import com.example.demo.post.controller.response.PostResponseDTO;
import com.example.demo.post.controller.response.PostsResponseDTO;
import com.example.demo.post.domain.Post;
import com.example.demo.post.service.PostService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @GetMapping("/posts/{id}")
  public PostResponseDTO getPost(@PathVariable Long id) {
    return postService.getPostDTO(id);
  }

  @GetMapping("/posts")
  public List<PostsResponseDTO> getAllPosts() {
    return postService.getAllPosts();
  }

  @PostMapping("/members/{id}")
  public void createPost(@PathVariable Long id, @RequestBody CreatePostRequest createPostRequest) {
    postService.createPost(id, createPostRequest);
  }

  @DeleteMapping("/posts/{id}")
  public void deletePost(@PathVariable Long id) {
    postService.deletePost(id);
  }
}
