package ta2khu75.com.webquiz.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ta2khu75.com.webquiz.entity.Quiz;
import ta2khu75.com.webquiz.entity.request.QuizRequest;
import ta2khu75.com.webquiz.entity.response.QuizResponse;
import ta2khu75.com.webquiz.exception.NotFoundException;
import ta2khu75.com.webquiz.mapper.QuizMapper;
import ta2khu75.com.webquiz.repository.QuizRepository;
import ta2khu75.com.webquiz.service.QuizSerivce;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuizServiceImpl implements QuizSerivce{
    QuizMapper mapper;
    QuizRepository repository;
    private Quiz findById(Long id){
        return repository.findById(id).orElseThrow(()->new NotFoundException("Could not found quiz with id " + id));
    }
    @Override
    public QuizResponse create(QuizRequest request) {
       Quiz quiz= mapper.toEntity(request);
       return mapper.toResponse(repository.save(quiz)); 
    }

    @Override
    public QuizResponse update(Long id, QuizRequest request) {
       Quiz quiz=findById(id);
       mapper.update(request, quiz); 
       return mapper.toResponse(repository.save(quiz));
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<QuizResponse> read(Pageable pageable) {
        return repository.findAll(pageable).map((quiz)->mapper.toResponse(quiz));
    }

    @Override
    public QuizResponse read(Long id) {
        return mapper.toResponse(findById(id));
    }
    
}
