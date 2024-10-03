package com.ta2khu75.quiz.service.impl;

import java.io.IOException;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.quiz.exception.UnAuthorizedException;
import com.ta2khu75.quiz.mapper.CommentMapper;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.model.entity.Blog;
import com.ta2khu75.quiz.model.entity.Comment;
import com.ta2khu75.quiz.model.request.CommentRequest;
import com.ta2khu75.quiz.model.response.CommentResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.BlogRepository;
import com.ta2khu75.quiz.repository.CommentRepository;
import com.ta2khu75.quiz.service.CommentService;
import com.ta2khu75.quiz.service.util.FileUtil;
import com.ta2khu75.quiz.service.util.FileUtil.Folder;
import com.ta2khu75.quiz.util.FunctionUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl extends BaseServiceImpl<CommentRepository, CommentMapper> implements CommentService {
	FileUtil fileUtil;
	BlogRepository blogRepository;
	AccountRepository accountRepository;

	public CommentServiceImpl(CommentRepository repository, CommentMapper mapper, FileUtil fileUtil,
			BlogRepository blogRepository, AccountRepository accountRepository) {
		super(repository, mapper);
		this.fileUtil = fileUtil;
		this.blogRepository = blogRepository;
		this.accountRepository=accountRepository;
	}

	@Override
	@Validated(Default.class)
	@Transactional
	public CommentResponse create(@Valid CommentRequest request, MultipartFile file) throws IOException {
		String email=SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new UnAuthorizedException("You must login first!"));
		Account account=accountRepository.findByEmail(email).orElseThrow(()->new UnAuthorizedException("You must login first!"));
		Comment comment = mapper.toEntity(request);
		comment.setAuthor(account);
		comment.setBlog(FunctionUtil.findOrThrow(request.getBlogId(), Blog.class, blogRepository::findById));
		fileUtil.saveFile(comment, file, Folder.COMMENT_FOLDER, Comment::setFilePath);
		return mapper.toResponse(repository.save(comment));
	}

	@Override
	@Validated(Default.class)
	public CommentResponse update(String id, @Valid CommentRequest request, MultipartFile file) throws IOException {
		Comment comment = FunctionUtil.findOrThrow(id, Comment.class, repository::findById);
		mapper.update(request, comment);
		fileUtil.saveFile(comment, file, Folder.COMMENT_FOLDER, Comment::setFilePath);
		return mapper.toResponse(repository.save(comment));
	}

	@Override
	public CommentResponse read(String id) {
		return mapper.toResponse(FunctionUtil.findOrThrow(id, Comment.class, repository::findById));
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

	@Override
	public PageResponse<CommentResponse> readPageByBlogId(String blogId, Pageable pageable) {
		return mapper.toPageResponse(repository.findByBlogId(blogId, pageable));
	}

}
