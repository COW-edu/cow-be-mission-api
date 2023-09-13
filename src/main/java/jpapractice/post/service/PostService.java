package jpapractice.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpapractice.member.entity.Member;
import jpapractice.member.service.MemberService;
import jpapractice.post.entity.Post;
import jpapractice.post.controller.dto.request.PostRequest;
import jpapractice.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

	private final PostRepository postRepository;
	private final MemberService memberService;

	public Long register(PostRequest postRequest, Long memberId) {
		Member member = memberService.findOne(memberId);
		Post post = postRequest.toEntity(member);
		post.changeMember(member);
		postRepository.save(post);
		return post.getId();
	}

	@Transactional(readOnly = true)
	public Post findPost(Long postId) {
		return postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
	}

	@Transactional(readOnly = true)
	public List<Post> findAllPosts(Long memberId) {
		Member member = memberService.findOne(memberId);
		return postRepository.findAllByMember(member);
	}

	public void delete(Long memberId, Long postId) throws Exception {
		Member member = memberService.findOne(memberId);
		List<Post> posts = member.getPosts();
		if (isCorrectPost(postId, posts)) {
			postRepository.deleteById(postId);
		} else {
			throw new Exception("사용자의 포스트가 아닙니다.");
		}
	}

	private boolean isCorrectPost(Long postId, List<Post> posts) {
		return posts.stream()
			.anyMatch(post -> post.getId().equals(postId));
	}

}
