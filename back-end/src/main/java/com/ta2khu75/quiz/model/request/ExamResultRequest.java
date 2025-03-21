package com.ta2khu75.quiz.model.request;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class ExamResultRequest {
	private Set<UserAnswerRequest> examAnswer=new HashSet<>();
}
