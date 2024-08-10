package com.ta2khu75.quiz.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import com.ta2khu75.quiz.entity.Answer;
import com.ta2khu75.quiz.entity.Quiz;
import com.ta2khu75.quiz.entity.request.AnswerRequest;
import com.ta2khu75.quiz.entity.response.AnswerResponse;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.AnswerMapper;
import com.ta2khu75.quiz.repository.AnswerRepository;
import com.ta2khu75.quiz.repository.QuizRepository;
import com.ta2khu75.quiz.service.AnswerService;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerServiceImpl implements AnswerService{
    AnswerRepository repository;
    QuizRepository quizRepository;
    AnswerMapper mapper;
    @Override
    public AnswerResponse create(AnswerRequest request) {
        Quiz quiz=findQuizById(request.quizId());
        Answer answer=mapper.toEntity(request);
        answer.setQuiz(quiz);
        return this.save(answer);
    }
    private Answer findById(Long id){
        return repository.findById(id).orElseThrow(()->new NotFoundException("Couldn't find answer with id " + id));
    }
    private Quiz findQuizById(Long id){
        return quizRepository.findById(id).orElseThrow(()->new NotFoundException("Couldn't find quiz with id " + id));
    }
    private AnswerResponse save(Answer answer){
        return mapper.toResponse(repository.save(answer));
    }
    @Override
    public AnswerResponse update(Long id, AnswerRequest request) {
        Answer answer=findById(id);
        mapper.update(request, answer);
        if(!answer.getQuiz().getId().equals(request.quizId())){
            Quiz quiz=findQuizById(request.quizId());
            answer.setQuiz(quiz);
        }
        return save(answer);
    }

    @Override
    public AnswerResponse read(Long id) {
        return mapper.toResponse(findById(id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
    @Override
    public Page<AnswerResponse> readPage(Pageable pageable) {
        return repository.findAll(pageable).map((answer)->mapper.toResponse(answer));
    }
    @Override
    public List<AnswerResponse> readAllByQuizId(Long id) {
        return repository.findByQuizId(id).stream().map(mapper::toResponse).toList();   
    }
}