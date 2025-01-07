package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.request.BlogRequest;
import com.ta2khu75.quiz.model.request.search.BlogSearchRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.BlogDetailResponse;
import com.ta2khu75.quiz.service.base.CrudFileService;

public interface BlogService extends CrudFileService<BlogRequest, BlogResponse, String> {
	PageResponse<BlogResponse> searchBlog(BlogSearchRequest blogSearchRequest);
	BlogDetailResponse readDetail(String id);
	Long countByAuthorEmail(String authorEmail);
	Long countByAuthorIdAndAccessModifier(String authorId, AccessModifier accessModifier);
}
