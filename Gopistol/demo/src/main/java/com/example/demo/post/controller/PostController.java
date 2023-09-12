package com.example.demo.post.controller;

import com.example.demo.post.controller.request.CreatePostRequest;
//import com.example.demo.post.controller.response.AllPostsResponseDTO;
import com.example.demo.post.controller.response.PostResponseDTO;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @GetMapping("/posts")
  public List<PostResponseDTO> getAllPosts() {
    List<Post> posts = postService.getAllPosts();
    // DTO객체에 넣어주기
    List<PostResponseDTO> postResponseDTOS = new ArrayList<>(posts.size());
    for (Post post : posts) {
      PostResponseDTO postResponseDTO = new PostResponseDTO();
      postResponseDTO.setPostId(post.getId());
      postResponseDTO.setMemberName(post.getMember().getName());
      postResponseDTO.setTitle(post.getTitle());
      postResponseDTO.setContent(post.getContent());
      postResponseDTO.setCommentCount(post.getCommentCount());

      postResponseDTOS.add(postResponseDTO);
    }
    return postResponseDTOS;
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
