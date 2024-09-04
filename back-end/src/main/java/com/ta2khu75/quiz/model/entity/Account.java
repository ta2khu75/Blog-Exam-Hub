package com.ta2khu75.quiz.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Account implements UserDetails {
	private static final long serialVersionUID = -6436446209727776976L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	@Column(unique = true, nullable = false)
	String email;
	@Column(unique = true, nullable = false)
	String displayName;
	@Column(nullable = false)
	String firstName;
	@Column(nullable = false)
	String lastName;
	@Column(nullable = false)
	String password;
	@Column(nullable = false)
	LocalDate birthday;
	String codeVerify;
	String refreshToken;
	boolean enabled;
	@Builder.Default
	boolean nonLocked = true;
	@CreatedDate
	@Column(updatable = false, nullable = false)
	LocalDateTime createdAt;
	@LastModifiedDate
	@Column(insertable = false)
	LocalDateTime updatedAt;
	@ManyToOne
	Role role;
	@OneToMany(mappedBy = "account")
	List<Exam> exams;
	@OneToMany(mappedBy = "account")
	List<ExamResult> examResults;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role))); // Add role as authority

		authorities.addAll(role.getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getName())).collect(Collectors.toList()));
		return authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonLocked() {
		return nonLocked;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
