package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.entity.id.ReportTargetId;
import com.ta2khu75.quiz.model.request.ReportTargetRequest;
import com.ta2khu75.quiz.model.response.ReportTargetResponse;

public interface ReportTargetService {
	ReportTargetResponse create(ReportTargetRequest request);
	void delete(ReportTargetId id);
}
