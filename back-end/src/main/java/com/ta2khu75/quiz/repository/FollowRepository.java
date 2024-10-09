package com.ta2khu75.quiz.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.quiz.model.entity.Follow;
import com.ta2khu75.quiz.model.entity.id.FollowId;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {
	Optional<Follow> findByFollowingIdAndFollowerEmail(String accountId, String accountEmail);
	Page<Follow> findByFollowingId(String followingId, Pageable pageable);
}
