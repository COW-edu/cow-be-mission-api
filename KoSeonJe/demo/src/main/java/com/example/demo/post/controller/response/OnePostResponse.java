package com.example.demo.post.controller.response;

import com.example.demo.comment.controller.response.AllCommentsResponse;
import com.example.demo.comment.domain.Comment;
import com.example.demo.post.domain.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OnePostResponse {

  private String title;
  private String content;
  private String author;
  private String createdDate;
  private List<AllCommentsResponse> allCommentsResponse;

  @Builder
  private OnePostResponse(String title, String content, String author, String createdDate, List<AllCommentsResponse> allCommentsResponse) {
    this.title = title;
    this.content = content;
    this.author = author;
    this.createdDate = createdDate;
    this.allCommentsResponse = allCommentsResponse;
  }

  public static OnePostResponse from(Post post) {
    return OnePostResponse.builder()
        .title(post.getTitle())
        .content(post.getContent())
        .author(post.getMember().getName())
        .createdDate(post.getCreatedDate().toString())
        .allCommentsResponse(post.getAllComment())
        .build();
  }
}
