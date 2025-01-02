package com.ta2khu75.quiz.model.entity.id;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportTargetId implements Serializable {
	private static final long serialVersionUID = -6196953391029199691L;
	private String authorId;
	private String targetId;
}