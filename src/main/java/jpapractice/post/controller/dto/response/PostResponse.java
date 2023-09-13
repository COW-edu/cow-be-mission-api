package jpapractice.post.controller.dto.response;

import java.time.LocalDate;
import java.util.List;
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
	public PostResponse(String title, String contents, String writer,
		LocalDate createDate, String comments) {
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.createDate = createDate;
		this.comments = comments;
	}

	public static PostResponse of(Post post) {
		return PostResponse.builder()
			.title(post.getTitle())
			.contents(post.getContents())
			.writer(post.getWriter())
			.createDate(post.getCreateDate())
			.comments(createCommentForm(post))
			.build();
	}

	private static String createCommentForm(Post post) {
		return post.getComments().stream()
			.map(Comment::getContents)
			.collect(Collectors.toList())
			.toString();
	}
}
