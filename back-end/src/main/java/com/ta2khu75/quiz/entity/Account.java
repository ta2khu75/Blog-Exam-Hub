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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(unique = true)
	String email;
	@Column(nullable = false)
	String password;
	String refreshToken;
	@Enumerated(EnumType.STRING)
	@Builder.Default
	Role role = Role.USER;
	@OneToMany(mappedBy = "account")
	List<ExamHistory> examHistories;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(String.format("ROLE_%s", role.name())));
	}

	@Override
	public String getUsername() {
		return email;
	}
}
