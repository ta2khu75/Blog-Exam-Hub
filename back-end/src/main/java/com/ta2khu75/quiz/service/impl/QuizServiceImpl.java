package com.ta2khu75.quiz.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import com.ta2khu75.quiz.model.request.QuizRequest;
import com.ta2khu75.quiz.model.response.QuizResponse;
import com.ta2khu75.quiz.enviroment.FolderEnv;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.QuizMapper;
import com.ta2khu75.quiz.model.entity.Exam;
import com.ta2khu75.quiz.model.entity.Quiz;
import com.ta2khu75.quiz.repository.ExamRepository;
import com.ta2khu75.quiz.repository.QuizRepository;
import com.ta2khu75.quiz.service.QuizSerivce;
import com.ta2khu75.quiz.service.util.CloudinaryUtil;

import jakarta.validation.Valid;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuizServiceImpl implements QuizSerivce{
    QuizMapper mapper;
    QuizRepository repository;
    ExamRepository examRepository;
    CloudinaryUtil cloudinaryService;
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
    public QuizResponse create(@Valid QuizRequest request, MultipartFile file) throws IOException {
        Exam exam= findExamById(request.getExamId());
        Quiz quiz= mapper.toEntity(request);
		saveFile(quiz, file);
        quiz.setExam(exam);
        return save(quiz);
    }
    private void saveFile(Quiz quiz, MultipartFile file) throws IOException{
    	if (file != null && !file.isEmpty()) {
			Map map = cloudinaryService.uploadFile(file, FolderEnv.QUIZ_FOLDER);
			quiz.setFilePath((String) map.get("url"));
		}
    }

    @Override
    public QuizResponse update(Long id, @Valid QuizRequest request, MultipartFile file) throws IOException {
       Quiz quiz=findById(id);
       mapper.update(request, quiz);
	   saveFile(quiz, file);
       if(!quiz.getExam().getId().equals(request.getExamId())){
        Exam exam= findExamById(request.getExamId());
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
	@Override
	public void deleteFile(Long id) {
		Quiz quiz = findById(id);
		quiz.setFilePath(null);
		repository.save(quiz);
	}
    
}
