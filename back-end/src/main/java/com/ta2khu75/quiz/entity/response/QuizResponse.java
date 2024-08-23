package com.ta2khu75.quiz.entity.response;

import com.ta2khu75.quiz.entity.request.QuizRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class QuizResponse extends QuizRequest{
	Long id;
	String filePath;
}
