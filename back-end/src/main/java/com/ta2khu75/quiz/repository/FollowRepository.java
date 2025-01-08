package com.ta2khu75.quiz.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.quiz.model.entity.Follow;
import com.ta2khu75.quiz.model.entity.id.FollowId;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {
	Page<Follow> findByFollowingId(String followingId, Pageable pageable);

	Set<Follow> findByFollowingId(String followingId);
}
