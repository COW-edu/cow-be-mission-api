package jpapractice.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jpapractice.member.entity.Member;
import jpapractice.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findById(final Long postId);
	List<Post> findAllByMember(final Member member);
}
