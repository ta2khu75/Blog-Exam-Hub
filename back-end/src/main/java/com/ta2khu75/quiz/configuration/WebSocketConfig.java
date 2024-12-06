package com.ta2khu75.quiz.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(@NonNull MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic", "/queue"); //Kích hoạt một "broker đơn giản" để chuyển tiếp tin nhắn đến các client đăng ký tại các điểm /topic (thường cho các thông báo công khai) và /queue (thường cho các thông báo riêng tư).
		config.setApplicationDestinationPrefixes("/app"); //Cấu hình prefix cho các tin nhắn do client gửi đến server.
		config.setUserDestinationPrefix("/account");
	}

	@Override
	public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
		registry.addEndpoint("/ws");
		registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
	}
}