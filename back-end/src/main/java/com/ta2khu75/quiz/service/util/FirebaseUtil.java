package com.ta2khu75.quiz.service.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.ta2khu75.quiz.service.util.FileUtil.Folder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FirebaseUtil {
	@Value("${firebase.bucket-name}")
	private String bucketName;
	@Value("${firebase.file-url}")
	private String fileUrl;
	@Value("${firebase.key-file}")
	private String keyFile;

	private String uploadFile(File file, String fileName) throws IOException {
		Path filePath = file.toPath();
		// Lấy MIME type của tệp
		String mimeType = Files.probeContentType(filePath);
		BlobId blobId = BlobId.of(bucketName, fileName); // Replace with your bucker name
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(mimeType).build();
		InputStream inputStream = FirebaseUtil.class.getClassLoader().getResourceAsStream(keyFile); // change the file
																									// name with your
																									// one
		Credentials credentials = GoogleCredentials.fromStream(inputStream);
		Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
		storage.create(blobInfo, Files.readAllBytes(file.toPath()));
		return String.format(fileUrl, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
	}

	private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
		File tempFile = new File(fileName);
		try (FileOutputStream fos = new FileOutputStream(tempFile)) {
			fos.write(multipartFile.getBytes());
			fos.close();
		}
		return tempFile;
	}

	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	public String upload(Folder folder, MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename(); // to get original file name
			fileName = String.format("%s_%s", folder.name(),
					UUID.randomUUID().toString().concat(this.getExtension(fileName))); // to generated
			// random
			// string
			// values for file name.
			File file = this.convertToFile(multipartFile, fileName); // to convert multipartFile to File
			String URL = this.uploadFile(file, fileName); // to get uploaded file										// link
			file.delete();
			return URL;
		} catch (Exception e) {
			e.printStackTrace();
			return "Image couldn't upload, Something went wrong";
		}
	}

}
