package jpapractice.comment.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jpapractice.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentRequest {

	@NotBlank(message = "작성자를 입력해주세요.")
	@Size(min = 2, max = 10, message = "작성자는 2자에서 10자 사이여야합니다.")
	private String writer;

	@NotBlank(message = "내용을 입력해주세요.")
	private String contents;

	public Comment toEntity() {
		return Comment.builder()
			.writer(writer)
			.contents(contents)
			.build();
	}
}
