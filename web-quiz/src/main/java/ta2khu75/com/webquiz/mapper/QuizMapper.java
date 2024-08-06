package ta2khu75.com.webquiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ta2khu75.com.webquiz.entity.Exam;
import ta2khu75.com.webquiz.entity.Quiz;
import ta2khu75.com.webquiz.entity.request.QuizRequest;
import ta2khu75.com.webquiz.entity.response.QuizResponse;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    @Mapping(source = "exam", target = "examId")
    QuizResponse toResponse(Quiz quiz);
    Quiz toEntity(QuizRequest request);
    void update(QuizRequest request, @MappingTarget Quiz quiz);
    default Long convertExam(Exam exam){
        return exam.getId();
    }
    
}
