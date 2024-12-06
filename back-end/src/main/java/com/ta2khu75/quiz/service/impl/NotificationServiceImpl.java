package com.ta2khu75.quiz.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.mapper.PageMapper;
import com.ta2khu75.quiz.model.TargetType;
import com.ta2khu75.quiz.model.entity.Notification;
import com.ta2khu75.quiz.model.entity.id.NotificationId;
import com.ta2khu75.quiz.model.response.NotificationResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.repository.NotificationRepository;
import com.ta2khu75.quiz.service.BlogService;
import com.ta2khu75.quiz.service.ExamService;
import com.ta2khu75.quiz.service.NotificationService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationServiceImpl implements NotificationService {
	NotificationRepository repository;
	BlogService blogService;
	ExamService examService;
	PageMapper pageMapper;

	@Override
	public Notification create(@Valid Notification request) {
		return repository.save(request);
	}

	@Override
	public Notification update(NotificationId id, @Valid Notification request) {
		return null;
	}

	@Override
	public Notification read(NotificationId id) {
		return null;
	}

	@Override
	public void delete(NotificationId id) {
		repository.deleteById(id);
	}

	@Override
	public PageResponse<NotificationResponse> readPageByAccountId(String accountId, Pageable pageable) {
		Page<Notification> page = repository.findByAccountId(accountId, pageable);
		Page<NotificationResponse> pageNotification = page.map(notification -> toResponse(notification));
		return pageMapper.toPageResponse(pageNotification);
	}

	private NotificationResponse toResponse(Notification notification) {
		NotificationResponse notificationResponse = new NotificationResponse();
//		notificationResponse.setId(new NotificationId(notification.getAccountId(), notification.getTargetId()));
		notificationResponse.setCreatedDate(notification.getCreatedDate());
		notificationResponse.setTarget(resolveTarget(notification));
		notificationResponse.setTargetType(notification.getTargetType());
		notificationResponse.setStatus(notification.getStatus());
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
