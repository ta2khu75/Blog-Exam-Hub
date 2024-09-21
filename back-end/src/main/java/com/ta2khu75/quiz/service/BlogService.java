package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.request.BlogRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.BlogDetailsResponse;

public interface BlogService extends BaseFileService<String, BlogRequest, BlogResponse>{
	PageResponse<BlogResponse> readPageAccountBlog(String id,Pageable pageable);
	PageResponse<BlogResponse> readPageMyBlog(Pageable pageable);
	PageResponse<BlogResponse> readPageBlogTag(String blogTagName, Pageable pageable);
	BlogDetailsResponse readDetail(String id);
	PageResponse<BlogResponse> readPage(Pageable pageable);
}
