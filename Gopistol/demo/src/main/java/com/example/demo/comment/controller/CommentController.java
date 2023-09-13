package com.example.demo.comment.controller;

import com.example.demo.comment.controller.commentRequest.CommentRequestDTO;
import com.example.demo.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/members/{memberId}/posts/{postId}")
  public void createComment(
      @PathVariable Long memberId,
      @PathVariable Long postId,
      @RequestBody CommentRequestDTO commentRequestDTO) {
    commentService.createComment(memberId, postId, commentRequestDTO);
  }
}
