package com.ta2khu75.quiz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ta2khu75.quiz.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByEmailAndRefreshToken(String email, String refreshToken);
}
