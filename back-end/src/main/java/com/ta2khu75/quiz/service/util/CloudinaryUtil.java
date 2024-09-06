package com.ta2khu75.quiz.service.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryUtil {
	private final Cloudinary cloudinary;

	public CloudinaryUtil(Cloudinary cloudinary) {
		this.cloudinary = cloudinary;
	}

	public Map uploadFile(MultipartFile file, String folderName) throws IOException {
		return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", folderName));
	}

	public Map uploadVideo(MultipartFile file, String folderName) throws IOException {
		return cloudinary.uploader().upload(file.getBytes(),
				ObjectUtils.asMap("resource_type", "video", "folder", folderName));
	}
}
