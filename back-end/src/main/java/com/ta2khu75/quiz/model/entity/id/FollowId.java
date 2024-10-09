package com.ta2khu75.quiz.model.entity.id;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowId implements Serializable {
	private static final long serialVersionUID = -4996620440907249903L;
	private String followerId;
	private String followingId;

}
