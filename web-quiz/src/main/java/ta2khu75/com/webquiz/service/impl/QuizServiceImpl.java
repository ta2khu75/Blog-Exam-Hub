package ta2khu75.com.webquiz.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ta2khu75.com.webquiz.entity.Exam;
import ta2khu75.com.webquiz.entity.Quiz;
import ta2khu75.com.webquiz.entity.request.QuizRequest;
import ta2khu75.com.webquiz.entity.response.QuizResponse;
import ta2khu75.com.webquiz.exception.NotFoundException;
import ta2khu75.com.webquiz.mapper.QuizMapper;
import ta2khu75.com.webquiz.repository.ExamRepository;
import ta2khu75.com.webquiz.repository.QuizRepository;
import ta2khu75.com.webquiz.service.QuizSerivce;
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
