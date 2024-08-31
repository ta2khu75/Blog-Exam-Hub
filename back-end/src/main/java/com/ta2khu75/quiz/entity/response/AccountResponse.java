package com.ta2khu75.quiz.entity.response;

import com.ta2khu75.quiz.entity.request.update.AccountInfoRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class AccountResponse extends AccountInfoRequest{
	String id;
	String email;
}
