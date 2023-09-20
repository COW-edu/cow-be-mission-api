package com.example.demo.post.controller.response;

import com.example.demo.post.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AllPostsResponse {
  //게시글의 id, 게시글의 제목, 작성자의 이름, 게시글의 댓글 개수를 리스트로 반환

  private Long postId;
  private String title;
  private String author;
  private int commentCount;

  @Builder
  private AllPostsResponse(Long postId, String title, String author, int commentCount) {
    this.postId = postId;
    this.title = title;
    this.author = author;
    this.commentCount = commentCount;
  }

  public static AllPostsResponse from(Post post) {
    return AllPostsResponse.builder()
        .postId(post.getPostId())
        .title(post.getTitle())
        .author(post.getMember().getName())
        .commentCount(post.getComments().size())
        .build();
  }
}
