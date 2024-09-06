package com.ta2khu75.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.quiz.model.entity.PermissionGroup;

public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Integer> {
	PermissionGroup findByName(String name);
}
