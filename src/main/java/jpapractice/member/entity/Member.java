package jpapractice.member.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jpapractice.member.controller.dto.request.UpdateMemberRequest;
import jpapractice.post.entity.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;

	@OneToMany(mappedBy = "member")
	private List<Post> posts = new ArrayList<>();

	@Builder
	private Member(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public void updateMember(UpdateMemberRequest request) {
		this.name = request.getName() != null ? request.getName() : this.name;
		this.email = request.getEmail() != null ? request.getEmail() : this.email;
		this.password = request.getPassword() != null ? request.getPassword() : this.password;
		updatePostWriter(request);
	}

	private void updatePostWriter(UpdateMemberRequest request) {
		String writer = request.getName();
		posts.stream()
			.forEach(post -> post.updateWriter(writer));
	}
}
