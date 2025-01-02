package com.ta2khu75.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.quiz.model.entity.ReportTarget;
import com.ta2khu75.quiz.model.entity.id.ReportTargetId;

public interface ReportTargetRepository extends JpaRepository<ReportTarget, ReportTargetId>{
}
