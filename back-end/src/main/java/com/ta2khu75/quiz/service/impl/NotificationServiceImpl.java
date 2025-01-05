package com.ta2khu75.quiz.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.mapper.NotificationMapper;
import com.ta2khu75.quiz.mapper.PageMapper;
import com.ta2khu75.quiz.model.TargetType;
import com.ta2khu75.quiz.model.entity.Notification;
import com.ta2khu75.quiz.model.response.NotificationResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.repository.NotificationRepository;
import com.ta2khu75.quiz.service.BlogService;
import com.ta2khu75.quiz.service.ExamService;
import com.ta2khu75.quiz.service.NotificationService;


@Service
public class NotificationServiceImpl extends BaseServiceImpl<NotificationRepository, NotificationMapper>
		implements NotificationService {

	private final BlogService blogService;
	private final ExamService examService;
	private final PageMapper pageMapper;

	public NotificationServiceImpl(NotificationRepository repository, NotificationMapper mapper,
			BlogService blogService, ExamService examService, PageMapper pageMapper) {
		super(repository, mapper);
		this.blogService = blogService;
		this.examService = examService;
		this.pageMapper = pageMapper;
	}

//	@Override
//	public Notification create(@Valid Notification request) {
//		return repository.save(request);
//	}
//
//	@Override
//	public Notification update(NotificationId id, @Valid Notification request) {
//		return null;
//	}
//
//	@Override
//	public Notification read(NotificationId id) {
//		return null;
//	}
//
//	@Override
//	public void delete(NotificationId id) {
//		repository.deleteById(id);
//	}

	@Override
	public PageResponse<NotificationResponse> readPageByAccountId(String accountId, Pageable pageable) {
		Page<Notification> page = repository.findByAccountId(accountId, pageable);
		Page<NotificationResponse> pageNotification = page.map(notification -> toResponse(notification));
		return pageMapper.toPageResponse(pageNotification);
	}

	private NotificationResponse toResponse(Notification notification) {
		NotificationResponse notificationResponse = mapper.toResponse(notification);
		notificationResponse.setTarget(resolveTarget(notification));
		return notificationResponse;
	}

	private Object resolveTarget(Notification notification) {
		if (notification.getTargetType() == TargetType.BLOG) {
			return blogService.read(notification.getTargetId());
		} else if (notification.getTargetType() == TargetType.EXAM) {
			return examService.read(notification.getTargetId());
		}
		throw new IllegalArgumentException("Unsupported target type: " + notification.getTargetType());
	}

}
