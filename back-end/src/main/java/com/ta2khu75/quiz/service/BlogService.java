package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.request.BlogRequest;
import com.ta2khu75.quiz.model.request.search.BlogSearch;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.service.base.CrudFileService;

public interface BlogService extends CrudFileService<BlogRequest, BlogResponse, String> {
	PageResponse<BlogResponse> search(BlogSearch blogSearchRequest);
	BlogResponse readDetail(String id);
	Long countByAuthorEmail(String authorEmail);
	Long countByAuthorIdAndAccessModifier(String authorId, AccessModifier accessModifier);
}
