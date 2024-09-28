package com.ta2khu75.quiz.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.BlogMapper;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.model.entity.Blog;
import com.ta2khu75.quiz.model.entity.BlogTag;
import com.ta2khu75.quiz.model.request.BlogRequest;
import com.ta2khu75.quiz.model.request.search.BlogSearchRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.BlogDetailsResponse;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.BlogRepository;
import com.ta2khu75.quiz.repository.BlogTagRepository;
import com.ta2khu75.quiz.service.BlogService;
import com.ta2khu75.quiz.service.util.FileUtil;
import com.ta2khu75.quiz.service.util.FileUtil.Folder;
import com.ta2khu75.quiz.util.FunctionUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;

@Service
@Validated
public class BlogServiceImpl extends BaseServiceImpl<BlogRepository, BlogMapper> implements BlogService {
	private final FileUtil fileUtil;
	private final AccountRepository accountRepository;
	private final BlogTagRepository blogTagRepository;

	public BlogServiceImpl(BlogRepository repository, BlogMapper mapper, FileUtil fileUtil,
			AccountRepository accountRepository, BlogTagRepository blogTagRepository) {
		super(repository, mapper);
		this.fileUtil = fileUtil;
		this.accountRepository = accountRepository;
		this.blogTagRepository = blogTagRepository;
	}

	private List<BlogTag> saveAll(List<String> blogTags) {
		Set<BlogTag> existingBlogTags = new HashSet<>(blogTagRepository.findAllByNameIn(blogTags));
		Map<String, BlogTag> existingBlogTagMap = existingBlogTags.stream()
				.collect(Collectors.toMap(BlogTag::getName, blogTag -> blogTag));
		List<BlogTag> newBlogTags = blogTagRepository
				.saveAll(blogTags.stream().map(blogTagName -> existingBlogTagMap.computeIfAbsent(blogTagName, n -> {
					BlogTag blogTag = new BlogTag();
					blogTag.setName(blogTag.getName());
					blogTag.setName(blogTagName);
					return blogTag;
				})).toList());
		existingBlogTags.addAll(blogTagRepository.saveAll(newBlogTags));
		return new ArrayList<>(existingBlogTags);
	}

	@Override
	@Validated({ Default.class })
	@Transactional
	public BlogResponse create(@Valid BlogRequest request, MultipartFile file) throws IOException {
		Blog blog = mapper.toEntity(request);
		String email = SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new NotFoundException("Email not found"));
		Account account = FunctionUtil.findOrThrow(email, Account.class, accountRepository::findByEmail);
		blog.setAuthor(account);
		fileUtil.saveFile(blog, file, Folder.BLOG_FOLDER, Blog::setImagePath);
		List<BlogTag> blogTags = this.saveAll(request.getBlogTags());
		blog.setBlogTags(blogTags);
		return save(repository.save(blog));
	}

	private BlogResponse save(Blog blog) {
		return mapper.toResponse(blog);
	}

	@Override
	@Validated({ Default.class })
	public BlogResponse update(String id, @Valid BlogRequest request, MultipartFile file) throws IOException {
		Blog blog = FunctionUtil.findOrThrow(id, Blog.class, repository::findById);
		mapper.update(request, blog);
		fileUtil.saveFile(blog, file, Folder.BLOG_FOLDER, Blog::setImagePath);
		List<BlogTag> blogTags = saveAll(request.getBlogTags());
		blog.setBlogTags(blogTags);
		blog.setLastModifiedAt(LocalDate.now());
		return save(repository.save(blog));
	}

	@Override
	public BlogResponse read(String id) {
		return save(FunctionUtil.findOrThrow(id, Blog.class, repository::findById));
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

	@Override
	public BlogDetailsResponse readDetail(String id) {
		return mapper.toDetailsResponse(FunctionUtil.findOrThrow(id, Blog.class, repository::findById));
	}

	@Override
	public PageResponse<BlogResponse> searchBlog(BlogSearchRequest blogSearchRequest) {
		Pageable pageable = Pageable.ofSize(blogSearchRequest.getSize()).withPage(blogSearchRequest.getPage() - 1);
		return mapper
				.toPageResponse(repository.searchBlog(blogSearchRequest.getTagName(), blogSearchRequest.getKeyword(),
						null, blogSearchRequest.getAuthorId(), blogSearchRequest.getAccessModifier(), pageable));
	}

}
