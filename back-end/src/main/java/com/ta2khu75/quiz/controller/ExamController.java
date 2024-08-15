package com.ta2khu75.quiz.controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import com.ta2khu75.quiz.entity.Exam;
import com.ta2khu75.quiz.entity.ExamLevel;
import com.ta2khu75.quiz.entity.ExamType;
import com.ta2khu75.quiz.entity.request.ExamRequest;
import com.ta2khu75.quiz.entity.response.ExamResponse;
import com.ta2khu75.quiz.entity.response.details.ExamDetailsResponse;
import com.ta2khu75.quiz.service.ExamService;
import com.ta2khu75.quiz.util.ObjectUtil;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/exam")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamController {
    ExamService service;
    @GetMapping("exam-type")
    public ResponseEntity<ExamType[]> getMethodName() {
        return ResponseEntity.ok(ExamType.values());
    }
    @GetMapping("exam-level")
    public ResponseEntity<ExamLevel[]> getMethodNam() {
        return ResponseEntity.ok(ExamLevel.values());
    }
    
    @PostMapping(consumes="multipart/form-data")
    public ResponseEntity<ExamResponse> create(@RequestPart("exam_request") String examRequestString, @RequestPart(name = "image", required = true) MultipartFile image) throws IOException {
        ExamRequest examRequest= ObjectUtil.toObject(examRequestString, ExamRequest.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(examRequest, image));
    }
    @GetMapping("{id}")
    public ResponseEntity<ExamResponse> getMethodName(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.read(id));
    }
    @GetMapping("details/{id}")
    public ResponseEntity<ExamDetailsResponse> getMethod(@PathVariable("id") Long id) {
	    return ResponseEntity.ok(service.readDetail(id));
    }
    @PutMapping(path = "{id}", consumes = "multipart/form-data")
    public ResponseEntity<ExamResponse> putMethodName(@PathVariable(name = "id") Long id, @RequestPart("exam_request") String examRequestString, @RequestPart(name="image", required = false) MultipartFile image) throws IOException {
        ExamRequest examRequest= ObjectUtil.toObject(examRequestString, ExamRequest.class);
        return ResponseEntity.ok(service.update(id, examRequest, image));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMethodName(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<Page<ExamResponse>> getMethodName(Pageable pageable) {
        return ResponseEntity.ok(service.readPage(pageable));
    }
    
    
}
