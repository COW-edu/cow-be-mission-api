package jpapractice.comment.entity;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jpapractice.common.BaseEntity;
import jpapractice.post.entity.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String writer;
	private String contents;

	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	public void changePost(Post post) {
		this.post = post;
		post.getComments().add(this);
		post.updateCommentAmount();
	}

	@Builder
	public Comment(String writer, String contents, Post post) {
		this.writer = writer;
		this.contents = contents;
		this.post = post;
	}
}
