package com.ta2khu75.quiz.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.quiz.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
	Optional<Account> findByEmail(String email);

	Optional<Account> findByEmailAndRefreshToken(String email, String refreshToken);

	Optional<Account> findByCodeVerify(String codeVerify);
	@Query("SELECT a FROM Account a WHERE a.displayName LIKE %:search% OR a.email LIKE %:search%")
    Page<Account> searchByDisplayNameOrEmail(@Param	("search") String search, Pageable pageable);
}
