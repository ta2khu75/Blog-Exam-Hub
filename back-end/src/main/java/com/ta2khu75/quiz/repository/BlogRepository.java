package com.ta2khu75.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, String> {
	Page<Blog> findByAuthorId(String id, Pageable pageable);
	Page<Blog> findByAuthorEmail(String id, Pageable pageable);
	@Query("select b from Blog b where b.author.id = :id and b.accessModifier = :accessModifier")
	Page<Blog> findByAccountIdAndAccessModifier(@Param("id") String id,
			@Param("accessModifier") AccessModifier accessModifier, Pageable pageable);

	@Query("SELECT b FROM Blog b JOIN b.blogTags bt WHERE bt.name = :tagName AND b.accessModifier = :accessModifier")
    Page<Blog> findByBlogTagAndAccessModifier(@Param("tagName") String tagName, @Param("accessModifier") AccessModifier accessModifier, Pageable pageable);	
//	@Query("select b from Blog b where b.examCategory.id = :id and b.accessModifier = :accessModifier")
//	Page<Blog> findByBlogTagAndAccessModifier(Long id, AccessModifier accessModifier, Pageable pageable);
}
