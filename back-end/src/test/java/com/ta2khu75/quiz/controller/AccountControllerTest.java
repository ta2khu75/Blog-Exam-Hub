package com.ta2khu75.quiz.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta2khu75.quiz.entity.request.AccountRequest;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.model.entity.Role;
import com.ta2khu75.quiz.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AccountService accountService;
	private AccountRequest accountRequest;
	private AccountResponse accountResponse;

	@BeforeEach
	private void initData() {
		accountRequest = new AccountRequest();
		accountRequest.setEmail("kien@gmail.com");
		accountRequest.setPassword("123");
		accountRequest.setConfirmPassword("123");
		accountResponse = AccountResponse.builder().id(3L).email("kien@gmail.com").role(Role.USER).build(); // new AccountResponse(3L, "kien@gmail.com", "USER");
	}

	@Test
	void createAccount_validRequest_success() throws Exception {
		// given
		ObjectMapper objectMapper = new ObjectMapper();
		String content = objectMapper.writeValueAsString(accountRequest);
		Mockito.when(accountService.create(ArgumentMatchers.any())).thenReturn(accountResponse);
		// when 
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/v1/account").contentType(MediaType.APPLICATION_JSON).content(content))
		//then
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("success").value(true))
				.andExpect(MockMvcResultMatchers.jsonPath("data.email").value("kien@gmail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("data.role").value("USER"))
				.andExpect(MockMvcResultMatchers.jsonPath("data.id").value(3L));
		}
	@Test
	void create_emailNull_fail() throws Exception {
		// given
		ObjectMapper objectMapper = new ObjectMapper();
		accountRequest = new AccountRequest();
		accountRequest.setEmail(null);
		accountRequest.setPassword("123");
		accountRequest.setConfirmPassword("123");
		String content = objectMapper.writeValueAsString(accountRequest);
		// when 
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/v1/account").contentType(MediaType.APPLICATION_JSON).content(content))
		//then
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("success").value(false))
				.andExpect(MockMvcResultMatchers.jsonPath("message_error").value("[Field error in object 'accountRequest' on field 'email': rejected value [null]; codes [NotBlank.accountRequest.email,NotBlank.email,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [accountRequest.email,email]; arguments []; default message [email]]; default message [must not be blank]]"));
		}
}
