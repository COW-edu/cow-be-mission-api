package jpapractice.post.controller.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jpapractice.member.entity.Member;
import jpapractice.post.entity.Post;
import lombok.Getter;

@Getter
public class PostRequest {

	private String title;
	private String contents;

	public Post toEntity(Member member) {
		 return Post.builder()
			.title(title)
			.contents(contents)
			.writer(member.getName())
		 	.createDate(LocalDate.now())
			.member(member)
			.build();
	}
}
