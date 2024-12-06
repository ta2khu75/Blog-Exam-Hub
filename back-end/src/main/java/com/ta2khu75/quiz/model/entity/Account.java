package com.ta2khu75.quiz.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = { "exams", "comments", "blogs", "examResults" })
public class Account extends EntityBase implements UserDetails {
	private static final long serialVersionUID = -6436446209727776976L;
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
	@ManyToOne
	Role role;
	@OneToMany(mappedBy = "author")
	@JsonIgnore
//	@JsonManagedReference("account-blog")
	List<Blog> blogs;
	@OneToMany(mappedBy = "author")
//	@JsonManagedReference("account-comment")
	@JsonIgnore
	List<Comment> comments;
	@OneToMany(mappedBy = "author")
	@JsonIgnore
	List<Exam> exams;
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	List<ExamResult> examResults;
	@OneToMany(mappedBy = "following")
	@JsonIgnore
	Set<Follow> followers;
	@OneToMany(mappedBy = "follower")
	@JsonIgnore
	Set<Follow> following;

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
