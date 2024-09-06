package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.model.entity.Answer;
import com.ta2khu75.quiz.model.entity.ExamResult;
import com.ta2khu75.quiz.model.entity.Role;
import com.ta2khu75.quiz.model.response.AnswerResponse;
import com.ta2khu75.quiz.model.response.ExamResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamResultDetailsResponse;

@Mapper(componentModel = "spring")
public interface ExamResultMapper {
	@Named("toExamHistoryResponse")
	@Mapping(target="exam.author", source="exam.account")
	@Mapping(target="exam.quizzes", source="exam.quizzes")
	ExamResultResponse toResponse(ExamResult examHistory);
	@Mapping(target="exam.author", source="exam.account")
	ExamResultDetailsResponse toDetailsResponse(ExamResult examHistory);

	@Mapping(target = "content", qualifiedByName = "toExamHistoryResponse")
	PageResponse<ExamResultResponse> toPageResponse(Page<ExamResult> page);
	default AnswerResponse toResponse(Answer answer) {
		if(answer == null) return null;
		AnswerResponse answerResponse = new AnswerResponse();
		answerResponse.setId(answer.getId());
		answerResponse.setCorrect(answer.getCorrect());
		answerResponse.setAnswer(answer.getAnswerString());
		return answerResponse;
	}
	default String toResponse(Role role) {
		if(role==null) return null;
		return role.getName();
	}
}
