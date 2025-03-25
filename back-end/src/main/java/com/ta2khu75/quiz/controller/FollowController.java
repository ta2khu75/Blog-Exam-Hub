package com.ta2khu75.quiz.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.model.response.BooleanResponse;
import com.ta2khu75.quiz.model.response.FollowResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.service.FollowService;

@RestController
@RequestMapping("${app.api-prefix}/follows")
public class FollowController extends BaseController<FollowService> {
	public FollowController(FollowService service) {
		super(service);
	}

	@GetMapping("/account/{accountId}")
	@EndpointMapping(name="Follow account")
	public ResponseEntity<FollowResponse> create(@PathVariable String accountId) {
		return ResponseEntity.ok(service.create(accountId));
	}

	@DeleteMapping("/account/{accountId}")
	@EndpointMapping(name="Unfollow account")
	public ResponseEntity<Void> delete(@PathVariable String accountId) {
		service.delete(accountId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/account/{accountId}/check")
	@EndpointMapping(name="Check follow account")
	public ResponseEntity<BooleanResponse> check(@PathVariable String accountId) {
		return ResponseEntity.ok(new BooleanResponse(service.read(accountId)!=null));
	}

	@GetMapping("/follower/{followingId}")
	@EndpointMapping(name="Read page follower")
	public ResponseEntity<PageResponse<FollowResponse>> readPage(@PathVariable("followingId") String followingId,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		return ResponseEntity.ok(service.readPage(followingId, pageable));
	}
}
