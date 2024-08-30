package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.quiz.entity.request.QuizRequest;
import com.ta2khu75.quiz.entity.response.QuizResponse;

import java.io.IOException;
import java.util.List;

public interface QuizSerivce {
	QuizResponse create(QuizRequest request, MultipartFile file) throws IOException;

	QuizResponse update(Long id, QuizRequest request, MultipartFile file) throws IOException;

	void delete(Long id);

	void deleteFile(Long id);

	Page<QuizResponse> read(Pageable pageable);

	QuizResponse read(Long id);

	List<QuizResponse> readByExamId(Long id);
}
