package com.example.demo.comment.controller;

import com.example.demo.comment.controller.request.CreateCommentRequest;
import com.example.demo.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/posts/{postId}/comments")
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  public void createComment(
      @RequestBody CreateCommentRequest createCommentRequest,
      @PathVariable("memberId") Long memberId,
      @PathVariable("postId") Long postId
  ) {
    commentService.createComment(createCommentRequest, memberId, postId);
  }

}
