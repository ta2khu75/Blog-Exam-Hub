package com.ta2khu75.quiz.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public record AccountRequest( @NotBlank @Email String email, @NotBlank String password, @NotBlank @JsonProperty("confirm_password") String confirmPassword) {
}
