package jpapractice.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpapractice.comment.controller.dto.request.CommentRequest;
import jpapractice.comment.controller.dto.response.CommentResponse;
import jpapractice.comment.entity.Comment;
import jpapractice.comment.repository.CommentRepository;
import jpapractice.post.entity.Post;
import jpapractice.post.service.PostService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostService postService;

	public CommentResponse register(Long postId, CommentRequest commentRequest) {
		Comment comment = commentRequest.toEntity();
		commentRepository.save(comment);

		Post post = postService.findPost(postId);
		comment.changePost(post);
		return CommentResponse.of(comment);
	}
}
