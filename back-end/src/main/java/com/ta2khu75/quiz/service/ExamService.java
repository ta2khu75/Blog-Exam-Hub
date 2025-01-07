package com.ta2khu75.quiz.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.request.ExamRequest;
import com.ta2khu75.quiz.model.request.search.ExamSearchRequest;
import com.ta2khu75.quiz.model.response.ExamResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamDetailResponse;
import com.ta2khu75.quiz.service.base.CrudFileService;

public interface ExamService extends CrudFileService<ExamRequest, ExamResponse, String> {
	PageResponse<ExamResponse> searchExam(ExamSearchRequest examSearchRequest);
	PageResponse<ExamResponse> mySearchExamNull(String keyword, Pageable pageable);
	ExamDetailResponse readDetail(String id);
	List<ExamResponse> myReadAllById(List<String> ids);
	Long countByAuthorEmail(String authorEmail);
	Long countByAuthorIdAndAccessModifier(String authorId, AccessModifier accessModifier);
}
