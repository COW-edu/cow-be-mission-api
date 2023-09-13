package jpapractice.member.controller.dto.request;

import jpapractice.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberRequest {

	private String name;
	private String email;
	private String password;

	public Member toEntity() {
		return Member.builder()
			.name(name)
			.email(email)
			.password(password)
			.build();
	}
}
