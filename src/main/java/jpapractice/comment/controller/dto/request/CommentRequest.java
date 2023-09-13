package jpapractice.comment.controller.dto.request;

import java.time.LocalDateTime;

import jpapractice.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentRequest {
	private String writer;
	private String contents;

	public Comment toEntity() {
		return Comment.builder()
			.writer(writer)
			.contents(contents)
			.build();
	}
}
