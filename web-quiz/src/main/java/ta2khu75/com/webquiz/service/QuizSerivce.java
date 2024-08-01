package ta2khu75.com.webquiz.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ta2khu75.com.webquiz.entity.request.QuizRequest;
import ta2khu75.com.webquiz.entity.response.QuizResponse;

public interface QuizSerivce {
    QuizResponse create(QuizRequest request);
    QuizResponse update(Long id, QuizRequest request);
    void delete(Long id);
    Page<QuizResponse> read(Pageable pageable);
    QuizResponse read(Long id);
}
