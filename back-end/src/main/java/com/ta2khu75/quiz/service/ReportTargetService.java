package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.request.ReportTargetRequest;
import com.ta2khu75.quiz.model.response.ReportTargetResponse;

public interface ReportTargetService {
	ReportTargetResponse create(ReportTargetRequest request);
	void delete(String id);
}
