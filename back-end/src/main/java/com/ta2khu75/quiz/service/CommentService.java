package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.request.CommentRequest;
import com.ta2khu75.quiz.model.response.CommentResponse;
import com.ta2khu75.quiz.model.response.PageResponse;

public interface CommentService extends BaseService<CommentRequest, CommentResponse, String> {
	PageResponse<CommentResponse> readPageByBlogId(String blogId, Pageable pageable);
}
