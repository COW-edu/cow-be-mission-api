package jpapractice.post.controller.dto.request;

import java.time.LocalDate;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jpapractice.member.entity.Member;
import jpapractice.post.entity.Post;
import lombok.Getter;

@Getter
public class PostRequest {

	@NotBlank(message = "post의 제목을 입력해주세요")
	@Size(min = 1, max = 20, message = "제목은 1자에서 20자 사이여야합니다.")
	private String title;

	@NotBlank(message = "내용을 입력해주세요.")
	private String contents;

	public Post toEntity(final Member member) {
		 return Post.builder()
			.title(title)
			.contents(contents)
		 	.createdAt(LocalDate.now())
			.member(member)
			.build();
	}
}
