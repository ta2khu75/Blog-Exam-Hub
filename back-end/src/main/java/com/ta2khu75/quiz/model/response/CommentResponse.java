package com.ta2khu75.quiz.model.response;

import java.time.LocalDate;

import com.ta2khu75.quiz.model.base.CommentBase;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentResponse extends CommentBase {
	Long id;
	String filePath;
	AccountResponse author;
	LocalDate createdAt;
	LocalDate updatedAt;
}
