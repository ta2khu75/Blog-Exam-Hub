package ta2khu75.com.webquiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ta2khu75.com.webquiz.entity.Answer;
import ta2khu75.com.webquiz.entity.Quiz;
import ta2khu75.com.webquiz.entity.request.AnswerRequest;
import ta2khu75.com.webquiz.entity.response.AnswerResponse;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    @Mapping(source = "quiz", target = "quizId")
    AnswerResponse toResponse(Answer answer);
    Answer toEntity(AnswerRequest request);
    void update(AnswerRequest request, @MappingTarget Answer answer);
    default Long convertQuiz(Quiz quiz){
        return quiz.getId();
    }
}