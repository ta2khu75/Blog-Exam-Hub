package com.ta2khu75.quiz.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.model.response.FollowResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.service.FollowService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${app.api-prefix}/follow")
public class FollowController {
	FollowService followService;

	@GetMapping("/account/{accountId}")
	public ResponseEntity<FollowResponse> follow(@PathVariable("accountId") String accountId) {
		return ResponseEntity.ok(followService.create(accountId));
	}

	@DeleteMapping("/account/{accountId}/delete")
	public ResponseEntity<Void> unFollow(@PathVariable("accountId") String accountId) {
		followService.delete(accountId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/account/{accountId}/check")
	public ResponseEntity<FollowResponse> checkFollow(@PathVariable("accountId") String accountId) {
		return ResponseEntity.ok(followService.read(accountId));
	}

	@GetMapping("/follower/{followingId}")
	public ResponseEntity<PageResponse<FollowResponse>> readPageFollower(
			@PathVariable("followingId") String followingId,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		return ResponseEntity.ok(followService.readPage(followingId, pageable));
	}
}
