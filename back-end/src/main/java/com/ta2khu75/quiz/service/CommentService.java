package com.ta2khu75.quiz.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.request.CommentRequest;
import com.ta2khu75.quiz.model.response.CommentResponse;
import com.ta2khu75.quiz.model.response.PageResponse;

public interface CommentService extends BaseFileService<Long, CommentRequest, CommentResponse> {
	PageResponse<CommentResponse> readPageByBlogId(String blogId, Pageable pageable);
}
