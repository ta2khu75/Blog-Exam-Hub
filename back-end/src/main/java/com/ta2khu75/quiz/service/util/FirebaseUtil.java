package com.ta2khu75.quiz.service.util;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.*;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.ta2khu75.quiz.service.util.FileUtil.Folder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FirebaseUtil {
	 private final Storage storage;
	 @Value("${firebase.storage-bucket}")
	private String storageBucket;
	private String getExtension(MultipartFile file) {
		String fileName=file.getOriginalFilename();
		return fileName.substring(fileName.lastIndexOf("."));
	}
	private String getFileNameFromMediaLink(String mediaLink) {
        URI uri = URI.create(mediaLink);
        String path = uri.getPath(); // Lấy phần đường dẫn của URL
        return path.substring(path.indexOf('/') + 1); // Bỏ dấu "/" ở đầu
    }

	public String upload(Folder folder,MultipartFile file) throws IOException {
			String fileName=UUID.randomUUID().toString().concat(this.getExtension(file));
			String filePath= String.format("BACKEND/%s/%s", folder.name(),fileName); // to generated
        Bucket bucket = storage.get(storageBucket);
        Blob blob = bucket.create(filePath, file.getInputStream(), file.getContentType());
        blob.toBuilder().setAcl(Collections.singletonList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))).build().update();
        return String.format("https://storage.googleapis.com/%s/%s", storageBucket, filePath);
    }
	public boolean delete(String fileLink) {
        try {
            String fileName = getFileNameFromMediaLink(fileLink);
            boolean deleted = storage.delete(storageBucket, fileName);
            return deleted;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	

}
