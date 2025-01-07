package com.ta2khu75.quiz.service.base;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

public interface CrudFileService <Request, Response, Id> {
    Response create(@Valid Request request, MultipartFile file)throws IOException;
    Response update(Id id,@Valid Request request, MultipartFile file)throws IOException;
    Response read(Id id);
	void delete(Id id);
}