package com.ta2khu75.quiz.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.ta2khu75.quiz.mapper.BlogMapper;
import com.ta2khu75.quiz.model.entity.Blog;
import com.ta2khu75.quiz.model.entity.BlogTag;
import com.ta2khu75.quiz.model.request.BlogRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.repository.BlogRepository;
import com.ta2khu75.quiz.repository.BlogTagRepository;
import com.ta2khu75.quiz.service.BlogService;
import com.ta2khu75.quiz.service.util.FileUtil;
import com.ta2khu75.quiz.service.util.FileUtil.Folder;
import com.ta2khu75.quiz.util.FunctionUtil;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Validated
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogServiceImpl implements BlogService {
	BlogRepository repository;
	BlogMapper mapper;
	FileUtil fileUtil;
	BlogTagRepository blogTagRepository;

	private List<BlogTag> saveAll(List<String> blogTags) {
		List<BlogTag> existingBlogTags = blogTagRepository.findAllByNameIn(blogTags);
		Map<String, BlogTag> existingBlogTagMap = existingBlogTags.stream()
				.collect(Collectors.toMap(BlogTag::getName, blogTag -> blogTag));
		existingBlogTags.addAll(blogTagRepository
				.saveAll(blogTags.stream().map(blogTagName -> existingBlogTagMap.computeIfAbsent(blogTagName, n -> {
					BlogTag blogTag = new BlogTag();
					blogTag.setName(blogTag.getName());
					blogTag.setName(blogTagName);
					return blogTag;
				})).collect(Collectors.toList())));
		return existingBlogTags;
	}

	@Override
	@Validated({ Default.class })
	public BlogResponse create(@Valid BlogRequest request, MultipartFile file) throws IOException {
		Blog blog = mapper.toEntity(request);
		fileUtil.saveFile(blog, file, Folder.BLOG_FOLDER, Blog::setImagePath);
		List<BlogTag> blogTags = saveAll(request.getBlogTags());
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

}
