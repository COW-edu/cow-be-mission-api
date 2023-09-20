package com.example.demo.post.service;

import com.example.demo.member.domain.Member;
import com.example.demo.member.service.MemberService;
import com.example.demo.post.controller.request.CreatePostRequest;
import com.example.demo.post.controller.response.AllPostsResponse;
import com.example.demo.post.controller.response.OnePostResponse;
import com.example.demo.post.domain.Post;
import com.example.demo.post.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  private final MemberService memberService;

  public void createPost(CreatePostRequest createPostRequest, Long memberId) {
    Member member = memberService.findOneUser(memberId);
    Post post = createPostRequest.toEntity(member);
    postRepository.save(post);
  }

  @Transactional(readOnly = true)
  public OnePostResponse getOnePost(Long postId) {
    Post post = findOnePost(postId);
    return OnePostResponse.from(post);

  }

  @Transactional(readOnly = true)
  public List<AllPostsResponse> getAllPosts() {
    List<Post> allPosts = postRepository.findAll();
    List<AllPostsResponse> allPostsResponse = allPosts.stream()
        .map(AllPostsResponse::from)
        .collect(Collectors.toList());
    return allPostsResponse;
  }

  public void deletePost(Long postId) {
    Post post = findOnePost(postId);
    postRepository.delete(post);
  }

  @Transactional(readOnly = true)
  public Post findOnePost(Long postId) {
    return postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 게시물입니다."));
  }
}
