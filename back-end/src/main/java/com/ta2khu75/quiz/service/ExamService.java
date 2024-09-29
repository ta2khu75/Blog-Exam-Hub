package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.request.ExamRequest;
import com.ta2khu75.quiz.model.request.search.ExamSearchRequest;
import com.ta2khu75.quiz.model.response.ExamResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamDetailsResponse;

public interface ExamService extends BaseFileService<String, ExamRequest, ExamResponse> {
	PageResponse<ExamResponse> searchExam(ExamSearchRequest examSearchRequest);
	ExamDetailsResponse readDetail(String id);
	Long countByAuthorEmail(String authorEmail);
	Long countByAuthorIdAndAccessModifier(String authorId, AccessModifier accessModifier);
}
