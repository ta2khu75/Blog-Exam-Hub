package ta2khu75.com.webquiz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ta2khu75.com.webquiz.entity.request.AnswerRequest;
import ta2khu75.com.webquiz.entity.response.AnswerResponse;
import java.util.List;
public interface AnswerService extends CrudService<Long, AnswerRequest , AnswerResponse>{
    Page<AnswerResponse> readPage(Pageable pageable);
    List<AnswerResponse> readAllByQuizId(Long id);
}