package jpapractice.post.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	private String writer;

	private int commentAmount;

	@CreatedDate
	private LocalDate createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	@Builder
	public Post(String title, String contents, String writer, LocalDate createDate, Member member) {
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.createDate = createDate;
		this.member = member;
	}

	public void changeMember(Member member) {
		this.member = member;
		member.getPosts().add(this);
	}

	public void plusCommentAmount() {
		this.commentAmount = comments.size();
	}

	public void updateWriter(String writer) {
		this.writer = writer;
	}
}
