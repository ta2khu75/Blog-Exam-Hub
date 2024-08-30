package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.quiz.entity.request.ExamRequest;
import com.ta2khu75.quiz.entity.response.ExamResponse;
import com.ta2khu75.quiz.entity.response.details.ExamDetailsResponse;

import java.io.IOException;

public interface ExamService {
   ExamResponse create(ExamRequest examRequest, MultipartFile file) throws IOException;
   ExamResponse update( Long id, ExamRequest examRequest, MultipartFile file) throws IOException;
   ExamResponse read(Long id);
   void delete(Long id);
   Page<ExamResponse> readPage(Pageable pageable);
   ExamDetailsResponse readDetail(Long id);
 
}
