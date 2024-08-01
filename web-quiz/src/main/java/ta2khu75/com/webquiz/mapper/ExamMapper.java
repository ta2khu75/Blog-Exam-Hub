package ta2khu75.com.webquiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ta2khu75.com.webquiz.entity.Account;
import ta2khu75.com.webquiz.entity.Exam;
import ta2khu75.com.webquiz.entity.request.AccountRequest;
import ta2khu75.com.webquiz.entity.request.ExamRequest;
import ta2khu75.com.webquiz.entity.response.AccountResponse;
import ta2khu75.com.webquiz.entity.response.ExamResponse;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    ExamResponse toResponse(Exam exam);
    Exam toEntity(ExamRequest request);
    void update(ExamRequest request, @MappingTarget Exam exam);
}
