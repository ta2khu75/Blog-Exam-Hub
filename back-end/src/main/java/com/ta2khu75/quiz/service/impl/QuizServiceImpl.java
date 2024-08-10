package com.ta2khu75.quiz.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import com.ta2khu75.quiz.entity.Exam;
import com.ta2khu75.quiz.entity.Quiz;
import com.ta2khu75.quiz.entity.request.QuizRequest;
import com.ta2khu75.quiz.entity.response.QuizResponse;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.QuizMapper;
import com.ta2khu75.quiz.repository.ExamRepository;
import com.ta2khu75.quiz.repository.QuizRepository;
import com.ta2khu75.quiz.service.QuizSerivce;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuizServiceImpl implements QuizSerivce{
    QuizMapper mapper;
    QuizRepository repository;
    ExamRepository examRepository;
    private Quiz findById(Long id){
        return repository.findById(id).orElseThrow(()->new NotFoundException("Could not found quiz with id " + id));
    }
    private Exam findExamById(Long id){
        return examRepository.findById(id).orElseThrow(()->new NotFoundException("Could not found exam with id " + id));
    }
    private QuizResponse save(Quiz quiz){
       return mapper.toResponse(repository.save(quiz)); 
    }
    @Override
    public QuizResponse create(QuizRequest request) {
        Exam exam= findExamById(request.examId());
        Quiz quiz= mapper.toEntity(request);
        quiz.setExam(exam);
        return save(quiz);
    }

    @Override
    public QuizResponse update(Long id, QuizRequest request) {
       Quiz quiz=findById(id);
       mapper.update(request, quiz); 
       if(!quiz.getExam().getId().equals(request.examId())){
        Exam exam= findExamById(request.examId());
        quiz.setExam(exam);
       }
       return mapper.toResponse(repository.save(quiz));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<QuizResponse> read(Pageable pageable) {
        return repository.findAll(pageable).map((quiz)->mapper.toResponse(quiz));
    }

    @Override
    public QuizResponse read(Long id) {
        return mapper.toResponse(findById(id));
    }
    @Override
    public List<QuizResponse> readByExamId(Long id) {
        return repository.findByExamId(id).stream().map((exam)->mapper.toResponse(exam)).toList();
    }
    
}
