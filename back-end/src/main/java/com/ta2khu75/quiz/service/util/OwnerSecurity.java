package com.ta2khu75.quiz.service.util;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ta2khu75.quiz.repository.BlogRepository;
import com.ta2khu75.quiz.repository.CommentRepository;
import com.ta2khu75.quiz.repository.QuizRepository;
import com.ta2khu75.quiz.util.SecurityUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OwnerSecurity {
	BlogRepository blogRepository;
	QuizRepository examRepository;
	CommentRepository commentRepository;

	public boolean isBlogOwner(String blogId) {
		Optional<?> optional = blogRepository.findByIdAndAuthorId(blogId, getId());
		return optional.isPresent();
	}

	public boolean isExamOwner(String examId) {
		Optional<?> optional = examRepository.findByIdAndAuthorId(examId, getId());
		return optional.isPresent();
	}

	public boolean isCommentOwner(String commentId) {
		Optional<?> optional = commentRepository.findByIdAndAuthorId(commentId, getId());
		return optional.isPresent();
	}

	private String getId() {
		return SecurityUtil.getCurrentUserLogin();
	}
}
