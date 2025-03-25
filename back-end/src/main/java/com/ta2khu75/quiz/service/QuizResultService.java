package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.request.QuizResultRequest;
import com.ta2khu75.quiz.model.request.search.QuizResultSearch;
import com.ta2khu75.quiz.model.response.QuizResultResponse;
import com.ta2khu75.quiz.service.base.SearchService;

public interface QuizResultService extends SearchService<QuizResultResponse, QuizResultSearch> {
	QuizResultResponse read(String quizId);
	QuizResultResponse create(String quizId);
	QuizResultResponse update(String id, QuizResultRequest request);
	QuizResultResponse readDetail(String id);
}
