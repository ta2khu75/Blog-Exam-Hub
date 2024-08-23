package com.ta2khu75.quiz.service;

import static org.mockito.ArgumentMatchers.any;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ta2khu75.quiz.entity.Account;
import com.ta2khu75.quiz.entity.Role;
import com.ta2khu75.quiz.entity.request.AccountRequest;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@SpringBootTest
public class AccountServiceTest {
	@Autowired
	private AccountService service;
	@MockBean
	private AccountRepository repository;
	
	private AccountRequest accountRequest;
	private AccountResponse accountResponse;
	private Account account;
	@BeforeEach
	private void initData() {
		account=Account.builder().id(3L).email("kien@gmail.com").password("123").role(Role.USER).build();
		accountRequest = new AccountRequest();
		accountRequest.setEmail("kien@gmail.com");
		accountRequest.setPassword("123");
		accountRequest.setConfirmPassword("123");
		accountResponse = new AccountResponse(3L, "kien@gmail.com", "USER");
	}
	@Test
	void create_validRequest_success() throws Exception {
		// given
		Mockito.when(repository.save(any())).thenReturn(account);
		// when 
		AccountResponse response= service.create(accountRequest);
		//then
		Assertions.assertThat(accountResponse.getEmail()).isEqualTo("kien@gmail.com");
		Assertions.assertThat(accountResponse.getRole()).isEqualTo("USER");
//		Assertions.assertThat(accountResponse.id(), response.id());
	}
}
