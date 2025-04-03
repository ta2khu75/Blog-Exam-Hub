package com.ta2khu75.quiz.configuration;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class FirebaseConfig {
	@Value("${firebase.adminsdk}")
	private String adminsdk;
	@Value("${firebase.storage-bucket}")
	private String storageBucket;

	@Bean
	GoogleCredentials googleCredentials() throws IOException {
		InputStream serviceAccount = new ClassPathResource(adminsdk).getInputStream();
		return GoogleCredentials.fromStream(serviceAccount);
	}

	@Bean
	Storage storage(GoogleCredentials credentials) throws IOException {
		return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
	}
//	@Bean
//	 FirebaseApp firebaseApp(GoogleCredentials credentials) throws IOException {
//		if (FirebaseApp.getApps().isEmpty()) {
//			 FirebaseOptions options = new FirebaseOptions.Builder()
//	                    .setStorageBucket(storageBucket)
//	                    .setCredentials(credentials)
//	                    .build();
//	            return FirebaseApp.initializeApp(options);
//		}
//	        return FirebaseApp.getInstance();
//	}
}
