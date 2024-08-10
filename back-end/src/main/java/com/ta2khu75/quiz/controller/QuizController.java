package com.ta2khu75.quiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.ta2khu75.quiz.entity.QuizType;
import com.ta2khu75.quiz.entity.request.QuizRequest;
import com.ta2khu75.quiz.entity.response.QuizResponse;
import com.ta2khu75.quiz.service.QuizSerivce;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${app.api-prefix}/quiz")
public class QuizController {
    QuizSerivce serivce;
    @PostMapping
    public ResponseEntity<QuizResponse> postMethodName(@Valid @RequestBody QuizRequest request) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(serivce.create(request));
    }
    @GetMapping("exam/{id}")
    public ResponseEntity<List<QuizResponse>> getMethodNam(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(serivce.readByExamId(id));
    }
    
    @GetMapping("quiz-type")
    public ResponseEntity<QuizType[]>getMethodName() {
        return ResponseEntity.ok(QuizType.values());
    }
    
    
    @GetMapping("{id}")
    public ResponseEntity<QuizResponse> getMethodName(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(serivce.read(id) );
    }
   @PutMapping("{id}")
   public ResponseEntity<QuizResponse> putMethodName(@PathVariable("id") Long id,@Valid @RequestBody QuizRequest request) {
       return ResponseEntity.status(HttpStatus.OK).body(serivce.update(id, request));
   } 
   @DeleteMapping("{id}")
   public ResponseEntity<Void> deleteMethodName(@PathVariable("id") Long id){
    serivce.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
    
}
