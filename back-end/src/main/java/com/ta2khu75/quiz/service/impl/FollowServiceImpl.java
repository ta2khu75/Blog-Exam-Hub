package com.ta2khu75.quiz.service.impl;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ta2khu75.quiz.exception.ExistingException;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.FollowMapper;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.model.entity.Follow;
import com.ta2khu75.quiz.model.entity.id.FollowId;
import com.ta2khu75.quiz.model.response.FollowResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.FollowRepository;
import com.ta2khu75.quiz.service.FollowService;
import com.ta2khu75.quiz.util.SecurityUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FollowServiceImpl implements FollowService {
	FollowRepository followRepository;
	AccountRepository accountRepository;
	FollowMapper mapper;

	@Override
	@Transactional
	public FollowResponse create(String followingId) {
		String followerEmail = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("You are not logged in"));
		Optional<Follow> existingFollow = followRepository.findByFollowingIdAndFollowerEmail(followingId,
				followerEmail);
		if (existingFollow.isPresent()) {
			throw new ExistingException("Already following this user");
		}
		Account following = accountRepository.findById(followingId)
				.orElseThrow(() -> new NotFoundException("Can't find account"));
		Account follower = accountRepository.findByEmail(followerEmail)
				.orElseThrow(() -> new NotFoundException("Can't find account"));
		Follow follow = new Follow();
		follow.setId(new FollowId(follower.getId(), following.getId()));
		follow.setFollower(follower);
		follow.setFollowing(following);
		follow.setFollowTime(Instant.now());
		return mapper.toResponse(followRepository.save(follow));
	}

	@Override
	@Transactional
	public void delete(String followingId) {
		String followerEmail = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("You are not logged in"));
		Follow existingFollow = followRepository.findByFollowingIdAndFollowerEmail(followingId, followerEmail)
				.orElseThrow(() -> new NotFoundException("Can't find follow"));
		followRepository.delete(existingFollow);
	}

	@Override
	public FollowResponse read(String followingId) {
		String followerEmail = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("You are not logged in"));
		Follow existingFollow = followRepository.findByFollowingIdAndFollowerEmail(followingId, followerEmail)
				.orElseThrow(() -> new NotFoundException("Can't find follow"));
		return mapper.toResponse(existingFollow);
	}

	@Override
	public PageResponse<FollowResponse> readPage(String followingId, Pageable pageable) {
		return mapper.toPageResponse(followRepository.findByFollowingId(followingId, pageable));
	}

}
