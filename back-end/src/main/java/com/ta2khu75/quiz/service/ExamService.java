package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.request.ExamRequest;
import com.ta2khu75.quiz.model.response.ExamResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamDetailsResponse;

public interface ExamService extends BaseFileService<String, ExamRequest, ExamResponse> {
   PageResponse<ExamResponse> readPage(Pageable pageable);
   ExamDetailsResponse readDetail(String id);
   PageResponse<ExamResponse> readPageMyExam(Pageable pageable);
   PageResponse<ExamResponse> readPageAccountExam(String id, Pageable pageable);
   PageResponse<ExamResponse> readPageCategoryExam(Long id, Pageable pageable);
}
