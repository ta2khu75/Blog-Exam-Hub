package com.ta2khu75.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.quiz.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, String> {
	Page<Comment> findByBlogId(String blogId, Pageable pageable);
}
