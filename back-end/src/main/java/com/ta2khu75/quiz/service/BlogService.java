package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.request.BlogRequest;
import com.ta2khu75.quiz.model.request.search.BlogSearchRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.BlogDetailsResponse;

public interface BlogService extends BaseFileService<String, BlogRequest, BlogResponse> {
	PageResponse<BlogResponse> searchBlog(BlogSearchRequest blogSearchRequest);
	BlogDetailsResponse readDetail(String id);
	Long countByAuthorEmail(String authorEmail);
	Long countByAuthorIdAndAccessModifier(String authorId, AccessModifier accessModifier);
}
