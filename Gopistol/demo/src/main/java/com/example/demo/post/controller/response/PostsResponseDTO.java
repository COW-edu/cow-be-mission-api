package com.example.demo.post.controller.response;

import com.example.demo.comment.domain.Comment;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class PostsResponseDTO {

  Long postId;
  String memberName;
  String title;
  String content;
  int commentCount;
}
