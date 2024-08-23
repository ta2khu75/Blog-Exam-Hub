package com.ta2khu75.quiz.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class Account implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(unique = true, nullable = false)
	String email;
	@Column(nullable = false)
	private String password;
	private String refreshToken;
	@Enumerated(EnumType.STRING)
	@Builder.Default
	Role role = Role.USER;
	@OneToMany(mappedBy = "account")
	private List<Exam> exams;
	@OneToMany(mappedBy = "account")
	private List<ExamHistory> examHistories;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(String.format("ROLE_%s", role.name())));
	}

	@Override
	public String getUsername() {
		return email;
	}
}
