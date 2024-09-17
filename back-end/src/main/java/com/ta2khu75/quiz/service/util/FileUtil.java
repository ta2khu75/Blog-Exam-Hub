package com.ta2khu75.quiz.service.util;

import java.io.IOException;
import java.util.function.BiConsumer;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileUtil {
	FirebaseUtil fireBaseUtil;

	public <T> void saveFile(T entity, MultipartFile file, Folder folder, BiConsumer<T, String> setFilePathFunction)
			throws IOException {
		if (file != null && !file.isEmpty()) {
			String fileUrl=fireBaseUtil.upload(folder,file);
			setFilePathFunction.accept(entity, fileUrl);
		}
	}

	public enum Folder {
		USER_FOLDER, EXAM_FOLDER, QUIZ_FOLDER, ANSWER_FOLDER, COMMENT_FOLDER, BLOG_FOLDER;
	}

}
