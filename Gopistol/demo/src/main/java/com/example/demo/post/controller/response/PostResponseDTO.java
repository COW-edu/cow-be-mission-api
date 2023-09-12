package com.example.demo.post.controller.response;

import com.example.demo.post.domain.Post;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class PostResponseDTO {

  Long postId;
  String memberName;
  String title;
  String content;
  int commentCount;

}
