package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.quiz.model.request.ExamRequest;
import com.ta2khu75.quiz.model.response.ExamResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamDetailsResponse;

import java.io.IOException;

public interface ExamService {
   ExamResponse create(ExamRequest examRequest, MultipartFile file) throws IOException;
   ExamResponse update( Long id, ExamRequest examRequest, MultipartFile file) throws IOException;
   ExamResponse read(Long id);
   void delete(Long id);
   PageResponse<ExamResponse> readPage(Pageable pageable);
   ExamDetailsResponse readDetail(Long id);
}
