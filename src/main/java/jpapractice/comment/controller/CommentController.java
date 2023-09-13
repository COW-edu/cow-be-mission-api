package jpapractice.comment.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jpapractice.comment.controller.dto.request.CommentRequest;
import jpapractice.comment.service.CommentService;
import jpapractice.member.entity.Member;
import jpapractice.member.service.MemberService;
import jpapractice.post.entity.Post;
import jpapractice.post.service.PostService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/posts/{postId}")
public class CommentController {

	private final CommentService commentService;
	private final PostService postService;

	@PostMapping("/comment")
	public String comment(@PathVariable Long postId,
		@RequestBody CommentRequest commentRequest) {
		commentService.register(postId, commentRequest);
		return commentRequest.getContents();
	}
}
