package jpapractice.comment.controller.dto.response;

import jpapractice.comment.entity.Comment;
import lombok.Builder;

public class CommentResponse {

	String contents;

	@Builder
	public CommentResponse(String contents) {
		this.contents = contents;
	}

	public static CommentResponse of(Comment comment) {
		return CommentResponse.builder()
			.contents(comment.getContents())
			.build();
	}
}
