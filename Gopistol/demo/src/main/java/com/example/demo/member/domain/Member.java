package com.example.demo.member.domain;

import com.example.demo.comment.domain.Comment;
import com.example.demo.post.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Table(name = "users") // Table 이름 바꾸고 싶으면 이렇게.
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 자동 추가, 생성자 접근제어자 설정 가능 (기본 = Protected)
@AllArgsConstructor // 모든 필드 생성자를 자동으로 생성
public class Member {

  @Id // PK로 사용하겠다.
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // IDENTITY: 기본 DB 방식을 사용할게요. Oracle 등 다른 예시 찾아보기
  private Long id;

  @Column(unique = true, nullable = false)
  private String authId;

  private String name;

  @OneToMany(mappedBy = "member") // 읽기전용 (연관관계의 주인이 아니므로 수정하면 안 되므로)
  private List<Post> posts = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Comment> comments = new ArrayList<>();

  // 정적 팩토리 메소드 패턴x
  public static Member create(String authId, String name) {
    return new Member(null, authId, name, null, null);
  }

  // Path Variable 을 통한 변경 감지로 이름 수정
  public void changeName(String name) {
    this.name = name;
  }

  public void addPost(Post post) {
    this.posts.add(post);
    post.setMember(this);
  }

  public void addComment(Comment comment) {
    this.comments.add(comment);
    comment.setMember(this);
  }
}
