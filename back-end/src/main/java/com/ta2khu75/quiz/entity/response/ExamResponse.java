package com.ta2khu75.quiz.entity.response;

import com.ta2khu75.quiz.entity.request.ExamRequest;

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
public class ExamResponse extends ExamRequest{
	Long id;
	String imagePath;
	AccountResponse author;
}