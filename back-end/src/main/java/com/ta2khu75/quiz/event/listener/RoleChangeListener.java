package com.ta2khu75.quiz.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.ta2khu75.quiz.event.RoleChangeEvent;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleChangeListener implements ApplicationListener<RoleChangeEvent> {

	@Override
	public void onApplicationEvent(RoleChangeEvent event) {
		// Khởi động lại context để áp dụng cấu hình bảo mật mới
		log.info("Role changed");
		log.info(event.getSource().toString());
	}
}
