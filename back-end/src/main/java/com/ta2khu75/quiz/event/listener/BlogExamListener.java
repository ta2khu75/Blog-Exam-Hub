package com.ta2khu75.quiz.event.listener;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.ta2khu75.quiz.event.BlogExamEvent;
import com.ta2khu75.quiz.model.entity.Follow;
import com.ta2khu75.quiz.model.entity.Notification;
import com.ta2khu75.quiz.repository.FollowRepository;
import com.ta2khu75.quiz.repository.NotificationRepository;
import com.ta2khu75.quiz.util.SecurityUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogExamListener implements ApplicationListener<BlogExamEvent> {
	NotificationRepository repository;
	FollowRepository followRepository;
	SimpMessagingTemplate messagingTemplate;

	@Override
	public void onApplicationEvent(@NonNull BlogExamEvent event) {
		String accountId = SecurityUtil.getCurrentUserLogin();
		Set<Follow> followers = followRepository.findByFollowingId(accountId);
		Set<Notification> notificationSet = followers.stream().map(follow -> {
			Notification notification = new Notification();
			notification.setTargetId(event.getTargetId());
			notification.setTargetType(event.getTargetType());
			notification.setAccountId(follow.getId().getFollowerId());
			notification.setAccount(follow.getFollower());
			return notification;
		}).collect(Collectors.toSet());
		List<Notification> notifications = repository.saveAll(notificationSet);
		notifications.forEach(notification -> {
			messagingTemplate.convertAndSendToUser(notification.getAccountId(), "/queue/notifications", notification);
		});
	}
}
