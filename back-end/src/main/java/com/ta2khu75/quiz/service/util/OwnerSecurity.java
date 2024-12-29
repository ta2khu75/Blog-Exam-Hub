package com.ta2khu75.quiz.service.util;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ta2khu75.quiz.exception.UnAuthenticationException;
import com.ta2khu75.quiz.repository.BlogRepository;
import com.ta2khu75.quiz.repository.CommentRepository;
import com.ta2khu75.quiz.repository.ExamRepository;
import com.ta2khu75.quiz.util.SecurityUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OwnerSecurity {
	BlogRepository blogRepository;
	ExamRepository examRepository;
	CommentRepository commentRepository;
	public boolean isBlogOwner (String blogId) {
			String username= getUsername();
		Optional<?> optional= blogRepository.findByIdAndAuthorEmail(blogId, username);
		return optional.isPresent();
	}
	public boolean isExamOwner (String examId) {
		String username= getUsername();
		Optional<?> optional= examRepository.findByIdAndAuthorEmail(examId, username);
		return optional.isPresent();
	}
	public boolean isCommentOwner (String commentId) {
		String username= getUsername();
		Optional<?> optional= commentRepository.findByIdAndAuthorEmail(commentId, username);
		return optional.isPresent();
	}
	private String getUsername() {
		return SecurityUtil.getCurrentUserLogin().orElseThrow(()->new UnAuthenticationException("You must be login"));
	}
}
