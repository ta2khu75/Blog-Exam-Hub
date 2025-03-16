package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.request.ExamResultRequest;
import com.ta2khu75.quiz.model.request.search.ExamResultSearch;
import com.ta2khu75.quiz.model.response.ExamResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamResultDetailResponse;

public interface ExamResultService {
	ExamResultResponse readByExamId(String examId);
	ExamResultResponse createByExamId(String examId);
	ExamResultDetailResponse scoreByExamId(String id, ExamResultRequest examResultRequest);
	PageResponse<ExamResultResponse> search(ExamResultSearch searchRequest);
	ExamResultDetailResponse readDetails(String id);
}
