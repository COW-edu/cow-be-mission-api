package jpapractice.comment.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jpapractice.comment.controller.dto.request.CommentRequest;
import jpapractice.comment.controller.dto.response.CommentResponse;
import jpapractice.comment.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/{memberId}/posts/{postId}")
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/comment")
	public CommentResponse comment(@PathVariable Long postId,
		@Valid @RequestBody CommentRequest commentRequest) {
		return commentService.register(postId, commentRequest);
	}
}
