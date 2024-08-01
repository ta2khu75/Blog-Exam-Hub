package ta2khu75.com.webquiz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ta2khu75.com.webquiz.entity.request.ExamRequest;
import ta2khu75.com.webquiz.entity.response.ExamResponse;

import java.io.IOException;

public interface ExamService {
   ExamResponse create(ExamRequest examRequest, MultipartFile file) throws IOException;
   ExamResponse update( Long id, ExamRequest examRequest, MultipartFile file) throws IOException;
   ExamResponse read(Long id);
   void delete(Long id);
   Page<ExamResponse> readPage(Pageable pageable);
}
