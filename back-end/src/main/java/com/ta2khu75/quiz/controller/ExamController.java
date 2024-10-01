package com.ta2khu75.quiz.controller;

import java.io.IOException;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta2khu75.quiz.exception.UnAuthorizedException;
import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.request.ExamRequest;
import com.ta2khu75.quiz.model.request.search.ExamSearchRequest;
import com.ta2khu75.quiz.model.response.CountResponse;
import com.ta2khu75.quiz.model.response.ExamResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamDetailsResponse;
import com.ta2khu75.quiz.service.ExamService;
import com.ta2khu75.quiz.util.SecurityUtil;

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
	ObjectMapper objectMapper;

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<ExamResponse> createExam(@RequestPart("exam_request") String examRequestString,
			@RequestPart(name = "image", required = true) MultipartFile image) throws IOException {
		ExamRequest examRequest = objectMapper.readValue(examRequestString, ExamRequest.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(examRequest, image));
	}

	@GetMapping("{id}")
	public ResponseEntity<ExamResponse> readExam(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.read(id));
	}

	@GetMapping("{id}/details")
	public ResponseEntity<ExamDetailsResponse> readDetailExam(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.readDetail(id));
	}

	@PutMapping(path = "{id}", consumes = "multipart/form-data")
	public ResponseEntity<ExamResponse> updateExam(@PathVariable(name = "id") String id,
			@RequestPart("exam_request") String examRequestString,
			@RequestPart(name = "image", required = false) MultipartFile image) throws IOException {
		ExamRequest examRequest = objectMapper.readValue(examRequestString, ExamRequest.class);
		return ResponseEntity.ok(service.update(id, examRequest, image));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteExam(@PathVariable("id") String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


	@GetMapping
	public ResponseEntity<PageResponse<ExamResponse>> searchExam(ExamSearchRequest examSearchRequest) {
		examSearchRequest.setAccessModifier(AccessModifier.PUBLIC);
		examSearchRequest.setAuthorEmail(null);
		return ResponseEntity.ok(service.searchExam(examSearchRequest));
	}

	@GetMapping("my-exam/count")
	public ResponseEntity<CountResponse> countMyExam() {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new UnAuthorizedException("You must login first!"));
		return ResponseEntity.ok(new CountResponse(service.countByAuthorEmail(email)));
	}

	@GetMapping("my-exam")
	public ResponseEntity<PageResponse<ExamResponse>> searchMyExam(ExamSearchRequest examSearchRequest) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new UnAuthorizedException("You must login first!"));
		examSearchRequest.setAuthorEmail(email);
		examSearchRequest.setAccessModifier(null);
		examSearchRequest.setAuthorId(null);
		return ResponseEntity.ok(service.searchExam(examSearchRequest));
	}
	@GetMapping("{authorId}/count")
	public ResponseEntity<CountResponse> countExamAuthor(@PathVariable("authorId") String id) {
		return ResponseEntity
				.ok(new CountResponse(service.countByAuthorIdAndAccessModifier(id, AccessModifier.PUBLIC)));
	}
}
