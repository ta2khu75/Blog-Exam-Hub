package com.ta2khu75.quiz.service.base;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

public interface CrudFileService <REQ, RES, Id> {
    RES create(@Valid REQ request, MultipartFile file)throws IOException;
    RES update(Id id,@Valid REQ request, MultipartFile file)throws IOException;
    RES read(Id id);
	void delete(Id id);
}