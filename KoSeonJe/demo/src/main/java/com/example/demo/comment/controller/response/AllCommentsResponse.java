package com.example.demo.comment.controller.response;

import com.example.demo.comment.domain.Comment;
import com.example.demo.post.controller.response.AllPostsResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AllCommentsResponse {

  private String content;

  @Builder
  private AllCommentsResponse(String content) {
    this.content = content;
  }

  public static AllCommentsResponse from(Comment comment){
    return AllCommentsResponse.builder()
        .content(comment.getContent())
        .build();
  }
}
