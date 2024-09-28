package com.ta2khu75.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, String> {
	@Query("SELECT b FROM Blog b JOIN b.blogTags bt WHERE " 
			+ "(:tagName IS NULL OR bt.name = :tagName) "
			+ "AND (:keyword IS NULL OR b.title LIKE %:keyword% OR b.author.displayName LIKE %:keyword%) "
			+ "AND (:authorEmail IS NULL OR b.author.email= :authorEmail) "
			+ "AND (:authorId IS NULL OR b.author.id = :authorId) "
			+ "AND (:accessModifier IS NULL OR b.accessModifier = :accessModifier)")
	Page<Blog> searchBlog(@Param("tagName") String tagName, @Param("keyword") String keyword, @Param("authorEmail") String authorEmail,
			@Param("authorId") String authorId, @Param("accessModifier") AccessModifier accessModifier,
			Pageable pageable);
}
