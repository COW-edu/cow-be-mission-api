package com.example.demo.post.controller;

import com.example.demo.post.controller.request.CreatePostRequest;
import com.example.demo.post.controller.response.AllPostsResponse;
import com.example.demo.post.controller.response.OnePostResponse;
import com.example.demo.post.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class PostController {

  private final PostService postService;

  @GetMapping("/posts/{postId}")
  public OnePostResponse getOnePost(@PathVariable("postId") Long postId) {
    return postService.getOnePost(postId);
  }

  @GetMapping("/posts")
  public List<AllPostsResponse> getAllPost() {
    return postService.getAllPosts();
  }

  @PostMapping("/{memberId}/posts")
  public void createPost(
      @RequestBody CreatePostRequest createPostRequest,
      @PathVariable("memberId") Long memberId) {
    postService.createPost(createPostRequest, memberId);
  }

  @DeleteMapping("/{memberId}/posts/{postId}")
  public void deletePost(@PathVariable("postId") Long postId) {
    postService.deletePost(postId);
  }


}
