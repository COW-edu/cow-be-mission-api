package com.example.demo.post.service;

import com.example.demo.comment.domain.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.post.controller.request.CreatePostRequest;
import com.example.demo.post.controller.response.PostResponseDTO;
import com.example.demo.post.domain.Post;
import com.example.demo.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final MemberRepository memberRepository;

  public PostResponseDTO getPostDTO(Long id) {
    Post post = postRepository.getReferenceById(id);
    Member member = post.getMember();
    List<Comment> comments = post.getComments();
    List<String> commentsContent = new ArrayList<>(comments.size());

    for (Comment comment : comments) {
      commentsContent.add(comment.getComment());
    }

    PostResponseDTO postResponseDTO = new PostResponseDTO();
    postResponseDTO.setTitle(post.getTitle());
    postResponseDTO.setContent(post.getContent());
    postResponseDTO.setWriter(member.getName());
    postResponseDTO.setCreateAt(post.getCreateAt());
    postResponseDTO.setCommentContents(commentsContent);

    return postResponseDTO;
  }
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
