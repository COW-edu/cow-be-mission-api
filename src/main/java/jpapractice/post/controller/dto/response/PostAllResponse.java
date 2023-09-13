package jpapractice.post.controller.dto.response;

import java.time.LocalDate;
import java.util.stream.Collectors;

import jpapractice.comment.entity.Comment;
import jpapractice.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostAllResponse {
	private Long id;
	private String title;
	private String writer;
	private int commentAmount;

	@Builder
	public PostAllResponse(Long id, String title, String writer, int commentAmount) {
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.commentAmount = commentAmount;
	}

	public static PostAllResponse of(Post post) {
		return PostAllResponse.builder()
			.id(post.getId())
			.title(post.getTitle())
			.writer(post.getWriter())
			.commentAmount(post.getCommentAmount())
			.build();
	}
}
