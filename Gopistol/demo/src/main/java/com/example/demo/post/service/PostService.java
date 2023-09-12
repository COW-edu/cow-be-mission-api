package com.example.demo.post.service;

import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.post.controller.request.CreatePostRequest;
import com.example.demo.post.domain.Post;
import com.example.demo.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final MemberRepository memberRepository;

  public List<Post> getAllPosts() {
    return postRepository.findAll();
  }
  public void createPost(Long id, CreatePostRequest createPostRequest) {
    Member member = memberRepository.getReferenceById(id);
    Post post = Post.create(member, createPostRequest.getTitle(), createPostRequest.getContent());
    member.addPost(post);
    postRepository.save(post);
  }

  public void deletePost(Long id) {
    Post post = postRepository.getReferenceById(id);
    postRepository.delete(post);
  }
}
