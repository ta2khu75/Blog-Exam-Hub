package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.request.CommentRequest;
import com.ta2khu75.quiz.model.response.CommentResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.service.base.CrudService;

public interface CommentService extends CrudService<CommentRequest, CommentResponse, String> {
	PageResponse<CommentResponse> readPageByBlogId(String blogId, Pageable pageable);
}
