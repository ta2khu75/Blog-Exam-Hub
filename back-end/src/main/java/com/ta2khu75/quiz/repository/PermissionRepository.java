package com.ta2khu75.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.quiz.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
