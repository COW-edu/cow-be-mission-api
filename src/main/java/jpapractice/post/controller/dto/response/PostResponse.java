package jpapractice.post.controller.dto.response;

import java.time.LocalDate;
import java.util.stream.Collectors;

import jpapractice.comment.entity.Comment;
import jpapractice.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponse {
	private String title;
	private String contents;
	private String writer;
	private LocalDate createDate;
	private String comments;

	@Builder
	public PostResponse(final String title, final String contents, final String writer,
		LocalDate createDate, String comments) {
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.createDate = createDate;
		this.comments = comments;
	}

	public static PostResponse of(final Post post) {
		return PostResponse.builder()
			.title(post.getTitle())
			.contents(post.getContents())
			.writer(post.getMember().getName()) // 이게 아니면 member의 이름이 바뀔 때 post의 writer를 함께 갱신해야함
			.createDate(post.getCreatedAt())
			.comments(createCommentForm(post))
			.build();
	}

	private static String createCommentForm(final Post post) {
		return post.getComments().stream()
			.map(Comment::getContents)
			.collect(Collectors.toList())
			.toString();
	}
}
