package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.request.QuizRequest;
import com.ta2khu75.quiz.model.response.QuizResponse;

import java.util.List;

public interface QuizSerivce extends FileCrudService<Long, QuizRequest, QuizResponse> {
	void deleteFile(Long id);

	Page<QuizResponse> read(Pageable pageable);

	QuizResponse read(Long id);

	List<QuizResponse> readByExamId(Long id);
}
