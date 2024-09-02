package com.ta2khu75.quiz.entity.response;

import com.ta2khu75.quiz.entity.ExamLevel;
import com.ta2khu75.quiz.entity.ExamType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponse {
	Long id;
	String title;
	Integer time;
	String description;
	String imagePath;
	ExamType examType;
	ExamLevel examLevel;
}