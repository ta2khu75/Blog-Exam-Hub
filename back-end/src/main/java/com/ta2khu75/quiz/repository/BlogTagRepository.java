package com.ta2khu75.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ta2khu75.quiz.model.entity.BlogTag;

public interface BlogTagRepository extends JpaRepository<BlogTag, String> {
	List<BlogTag> findAllByNameIn(List<String> name); 
}
