package com.example.demo.comment.service;

import com.example.demo.comment.controller.commentRequest.CommentRequestDTO;
import com.example.demo.comment.domain.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.member.domain.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.post.domain.Post;
import com.example.demo.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final MemberRepository memberRepository;
  private final PostRepository postRepository;

  public void createComment(Long memberId, Long postId, CommentRequestDTO commentRequestDTO) {
    Member member = memberRepository.getReferenceById(memberId);
    Post post = postRepository.getReferenceById(postId);
    Comment comment = Comment.create(member, post, commentRequestDTO.getComment());
    member.addComment(comment);
    post.addComment(comment);
    commentRepository.save(comment);
  }
}
