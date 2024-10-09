package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.response.FollowResponse;
import com.ta2khu75.quiz.model.response.PageResponse;

public interface FollowService {
	FollowResponse create(String followingId);

	void delete(String followingId);

	FollowResponse read(String followingId);

	PageResponse<FollowResponse> readPage(String followingId, Pageable pageable);
}
