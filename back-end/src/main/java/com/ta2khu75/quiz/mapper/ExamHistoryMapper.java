package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.entity.Answer;
import com.ta2khu75.quiz.entity.ExamHistory;
import com.ta2khu75.quiz.entity.Role;
import com.ta2khu75.quiz.entity.response.AnswerResponse;
import com.ta2khu75.quiz.entity.response.ExamHistoryResponse;
import com.ta2khu75.quiz.entity.response.PageResponse;
import com.ta2khu75.quiz.entity.response.details.ExamHistoryDetailsResponse;

@Mapper(componentModel = "spring")
public interface ExamHistoryMapper {
	@Named("toExamHistoryResponse")
	@Mapping(target="exam.author", source="account")
	ExamHistoryResponse toResponse(ExamHistory examHistory);
	ExamHistoryDetailsResponse toDetailsResponse(ExamHistory examHistory);

	@Mapping(target = "content", qualifiedByName = "toExamHistoryResponse")
	PageResponse<ExamHistoryResponse> toPageResponse(Page<ExamHistory> page);
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
