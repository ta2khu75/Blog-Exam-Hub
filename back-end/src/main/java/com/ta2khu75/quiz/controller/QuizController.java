package com.ta2khu75.quiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import com.ta2khu75.quiz.model.response.QuizResponse;
import com.ta2khu75.quiz.service.QuizService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${app.api-prefix}/quiz")
public class QuizController {
	QuizService service;
//	ObjectMapper objectMapper;

//	@PostMapping(consumes = "multipart/form-data")
//	public ResponseEntity<QuizResponse> createQuiz(@RequestPart("quiz") String request, @RequestPart(name="file", required = false) MultipartFile file) throws IOException {
//		QuizRequest quizRequest = objectMapper.readValue(request, QuizRequest.class);
//		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(quizRequest, file));
//	}	

	@GetMapping("exam/{id}")
	public ResponseEntity<List<QuizResponse>> readAllQuizExam(@PathVariable("id") String id) {
		return ResponseEntity.ok().body(service.readByExamId(id));
	}

	@GetMapping("{id}")
	public ResponseEntity<QuizResponse> readQuiz(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.read(id));
	}

//	@PutMapping(value = "{id}", consumes = "multipart/form-data")
//	public ResponseEntity<QuizResponse> updateQuiz(@PathVariable("id") Long id,
//			@RequestPart("quiz") String request, @RequestPart(name="file", required = false) MultipartFile file) throws IOException {
//		QuizRequest quizRequest = objectMapper.readValue(request, QuizRequest.class);
//		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, quizRequest, file));
//	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteQuiz(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	@DeleteMapping("image/{id}")
	public ResponseEntity<Void> deleteQuizImage(@PathVariable("id") Long id) {
		service.deleteFile(id);
		return ResponseEntity.noContent().build();
	}

}
