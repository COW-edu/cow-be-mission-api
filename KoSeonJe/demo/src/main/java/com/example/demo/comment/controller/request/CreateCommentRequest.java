package com.example.demo.comment.controller.request;

import com.example.demo.comment.domain.Comment;
import com.example.demo.member.domain.Member;
import com.example.demo.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {

  private String content;

  public Comment toEntity(Member member, Post post){
    return Comment.builder()
        .content(content)
        .member(member)
        .post(post)
        .build();
  }
}
