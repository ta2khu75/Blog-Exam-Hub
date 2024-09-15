package com.ta2khu75.quiz.model.entity;

import com.ta2khu75.quiz.model.HTTPMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = {"permissionGroup"})
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(unique = true, nullable = false)
	String name;
	@Column(nullable = false)
	String path;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	HTTPMethod method;
	@ManyToOne
	PermissionGroup permissionGroup;
}
