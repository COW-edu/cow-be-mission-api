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
	public PostAllResponse(final Long id, final String title, final String writer, final int commentAmount) {
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.commentAmount = commentAmount;
	}

	public static PostAllResponse of(final Post post) {
		return PostAllResponse.builder()
			.id(post.getId())
			.title(post.getTitle())
			.writer(post.getMember().getName())
			.commentAmount(post.getCommentAmount())
			.build();
	}
}
