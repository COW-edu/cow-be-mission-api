package jpapractice.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jpapractice.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
