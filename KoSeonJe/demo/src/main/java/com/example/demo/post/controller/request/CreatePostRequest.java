package com.example.demo.post.controller.request;

import com.example.demo.member.domain.Member;
import com.example.demo.post.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostRequest {

  private String title;
  private String content;

  public Post toEntity(Member member){
    return Post.builder()
        .title(title)
        .content(content)
        .member(member)
        .build();
  }
}
