package com.group7.fruitswebsite.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {

	private static String UPLOAD_DIR = System.getProperty("user.dir") + "\\uploads\\category";

	public static String saveUploadedFiles(MultipartFile[] files) throws IOException {

		String targetUploadDir = UPLOAD_DIR + File.separator + createPathFromCurrentDate();
		File uploadDir = new File(targetUploadDir);
		// Make sure directory exists!
		uploadDir.mkdirs();

		StringBuilder sb = new StringBuilder();

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue;
			}
			String uploadFilePath = targetUploadDir + File.separator  + file.getOriginalFilename();

			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadFilePath);
			Files.write(path, bytes);

			sb.append(uploadFilePath);
		}
		return sb.toString();
	}

	private static String createPathFromCurrentDate() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm");
		String currentDate = simpleDateFormat.format(date);
		return currentDate;
	}
}
