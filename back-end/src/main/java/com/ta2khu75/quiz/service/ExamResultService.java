package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.request.ExamResultRequest;
import com.ta2khu75.quiz.model.request.search.ExamResultSearch;
import com.ta2khu75.quiz.model.response.ExamResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;

public interface ExamResultService {
	ExamResultResponse readByExamId(String examId);
	ExamResultResponse createByExamId(String examId);
	ExamResultResponse scoreByExamId(String id, ExamResultRequest examResultRequest);
	PageResponse<ExamResultResponse> search(ExamResultSearch searchRequest);
	ExamResultResponse readDetails(String id);
}
