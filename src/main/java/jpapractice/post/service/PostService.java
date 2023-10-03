package jpapractice.post.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpapractice.member.entity.Member;
import jpapractice.member.service.MemberService;
import jpapractice.post.controller.dto.response.PostAllResponse;
import jpapractice.post.controller.dto.response.PostResponse;
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

	public PostResponse register(PostRequest postRequest, Long memberId) {
		Member member = memberService.findOne(memberId);
		Post post = postRequest.toEntity(member);
		post.changeMember(member);
		postRepository.save(post);
		return PostResponse.of(post);
	}

	@Transactional(readOnly = true)
	public Post findPost(Long postId) {
		return postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 포스트가 존재하지 않습니다."));
	}

	@Transactional(readOnly = true)
	public List<PostAllResponse> findAllPosts(Long memberId) {
		Member member = memberService.findOne(memberId);
		List<Post> posts = postRepository.findAllByMember(member);
		return posts.stream()
			.map(PostAllResponse::of)
			.collect(Collectors.toList());
	}

	public void delete(Long memberId, Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("[Error] 사용자가 존재하지 않습니다."));
		if (post.isCorrectMember(memberId)) {
			postRepository.deleteById(postId);
		} else {
			throw new IllegalArgumentException("[Error] 사용자의 포스트가 아닙니다.");
		}
	}

}
