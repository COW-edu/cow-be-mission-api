package com.example.demo.post.domain;

import com.example.demo.comment.domain.Comment;
import com.example.demo.member.domain.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

//@Table(name = "users") // Table 이름 바꾸고 싶으면 이렇게.
@Data
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 자동 추가, 생성자 접근제어자 설정 가능 (기본 = Protected)
@AllArgsConstructor
public class Post {

  @Id // PK로 사용하겠다.
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // IDENTITY: 기본 DB 방식을 사용할게요. Oracle 등 다른 예시 찾아보기
  private Long id;

  private String title;

  private String content;

  private int commentCount;

  private LocalDateTime createAt;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();

  public static Post create(Member member, String title, String content) {
    return new Post(null, title, content, 0, LocalDateTime.now(), member, null);
  }

  public List<Comment> getComments() {
    return this.comments;
  }

  @Override
  public String toString() {
    return "Post{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", commentCount=" + commentCount +
        ", createAt=" + createAt +
        ", member=" + (member != null ? member.getName() : null) + // 작성자 정보 출력 (예: 작성자의 사용자 이름)
        ", comments=" + comments.size() + " comments" + // 댓글 수 출력
        '}';
  }
  public void addComment(Comment comment) {
    this.getComments().add(comment);
    comment.setPost(this);
    // 댓글 개수 1 증가시킴
    plusCommentCount();
  }

  private void plusCommentCount() {
    this.commentCount++;
  }
}

