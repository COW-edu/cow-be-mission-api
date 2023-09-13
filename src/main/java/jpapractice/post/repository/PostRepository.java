package jpapractice.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpapractice.member.entity.Member;
import jpapractice.post.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findById(Long postId);
	List<Post> findAllByMember(Member member);
}
