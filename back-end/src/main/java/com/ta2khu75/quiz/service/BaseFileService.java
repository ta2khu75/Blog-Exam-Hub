package com.ta2khu75.quiz.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

public interface BaseFileService <D, T, E> {
    E create(@Valid T request, MultipartFile file)throws IOException;
    E update(D id,@Valid T request, MultipartFile file)throws IOException;
    E read(D id);
	void delete(D id);
}