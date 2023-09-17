package jpapractice.post.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jpapractice.post.controller.dto.response.PostAllResponse;
import jpapractice.post.controller.dto.response.PostResponse;
import jpapractice.post.entity.Post;
import jpapractice.post.controller.dto.request.PostRequest;
import jpapractice.post.service.PostService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("members/{memberId}/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/new")
	public PostResponse post(@PathVariable final Long memberId, @Valid @RequestBody final PostRequest postRequest) {
		return postService.register(postRequest, memberId);
	}

	@GetMapping("/{postId}")
	public PostResponse findPost(@PathVariable final Long postId) {
		Post post = postService.findPost(postId);
		return PostResponse.of(post);
	}

	@GetMapping
	public List<PostAllResponse> findPosts(@PathVariable final Long memberId) {
		return postService.findAllPosts(memberId);
	}

	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable final Long postId, @PathVariable final Long memberId) {
		postService.delete(memberId, postId);
	}
}
