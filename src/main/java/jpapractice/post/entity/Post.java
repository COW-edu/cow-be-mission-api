package jpapractice.post.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jpapractice.comment.entity.Comment;
import jpapractice.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String contents;

	private int commentAmount;

	@CreatedDate
	private LocalDate createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	@Builder
	public Post(final String title, final String contents, final LocalDate createdAt, final Member member) {
		this.title = title;
		this.contents = contents;
		this.createdAt = createdAt;
		this.member = member;
	}

	public void changeMember(final Member member) {
		this.member = member;
		member.getPosts().add(this);
	}

	public void updateCommentAmount() {
		this.commentAmount = comments.size();
	}

	public boolean isCorrectMember(final Long memberId) {
		return Objects.equals(member.getId(), memberId);
	}

}
