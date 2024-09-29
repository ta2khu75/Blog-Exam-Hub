package com.ta2khu75.quiz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, String> {
	@Query("SELECT b FROM Blog b JOIN b.blogTags bt WHERE " + "(:blogTagNames IS NULL OR bt.name IN (:blogTagNames)) "
			+ "AND (:keyword IS NULL OR b.title LIKE %:keyword% OR b.author.displayName LIKE %:keyword%) "
			+ "AND (:authorEmail IS NULL OR b.author.email= :authorEmail) "
			+ "AND (:authorId IS NULL OR b.author.id = :authorId) "
			+ "AND (:minView IS NULL OR b.viewCount >= :minView) "
			+ "AND (:maxView IS NULL OR b.viewCount <= :maxView) "
			+ "AND (:accessModifier IS NULL OR b.accessModifier = :accessModifier)")
	Page<Blog> searchBlog(@Param("blogTagNames") List<String> blogTagNames, @Param("keyword") String keyword,
			@Param("authorEmail") String authorEmail, @Param("authorId") String authorId,
			@Param("minView") Integer minView, @Param("maxView") Integer maxView,
			@Param("accessModifier") AccessModifier accessModifier, Pageable pageable);
	Long countByAuthorIdAndAccessModifier(String authorId, AccessModifier accessModifier);
	Long countByAuthorEmail(String authorEmail);
}
