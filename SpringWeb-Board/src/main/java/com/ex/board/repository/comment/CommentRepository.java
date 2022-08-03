package com.ex.board.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ex.board.Security.SiteUser;
import com.ex.board.entity.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	long countByAuthor(SiteUser author);
}
