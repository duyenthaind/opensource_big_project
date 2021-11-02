package com.group7.fruitswebsite.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.group7.fruitswebsite.config.ApplicationConfig;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Log4j
public class ImageUtil {
    private ImageUtil() {

    }

    public static String saveUploadedFiles(MultipartFile[] files) {
        try {
            return uploadFilesAndGetPath(files, ApplicationConfig.ROOT_UPLOAD_DIR);
        } catch (Exception ex) {
            log.error("Error save uploaded file, ", ex);
        }
        return StringUtils.EMPTY;
    }

    public static String saveUploadedFiles(MultipartFile[] files, String path) {
        try {
            return uploadFilesAndGetPath(files, path);
        } catch (Exception ex) {
            log.error("Error when upload file, ", ex);
        }
        return StringUtils.EMPTY;
    }

    public static List<String> saveUploadedMultiFiles(MultipartFile[] files, String path) {
        try {
            return uploadMultiFilesAndGetPath(files, path);
        } catch (Exception ex) {
            log.error("Error when upload file, ", ex);
        }
        return Collections.emptyList();
    }

    private static String uploadFilesAndGetPath(MultipartFile[] files, String path) throws IOException {
        StringBuilder result = new StringBuilder();
        String targetUploadDir = path + File.separator + createPathFromCurrentDate();
        File uploadDir = new File(targetUploadDir);
        // Make sure directory exists!
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) {
                continue;
            }
            String uploadFilePath = targetUploadDir + File.separator + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path currentPath = Paths.get(uploadFilePath);
            Files.write(currentPath, bytes);

            uploadFilePath = uploadFilePath.replace(ApplicationConfig.ROOT_UPLOAD_DIR + File.separator, StringUtils.EMPTY);
            result.append(uploadFilePath);
        }
        return result.toString();
    }

    private static List<String> uploadMultiFilesAndGetPath(MultipartFile[] files, String path) throws IOException {
        List<String> results = new ArrayList<>();
        String targetUploadDir = path + File.separator + createPathFromCurrentDate();
        File uploadDir = new File(targetUploadDir);
        // Make sure directory exists!
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) {
                continue;
            }
            String uploadFilePath = targetUploadDir + File.separator + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path currentPath = Paths.get(uploadFilePath);
            Files.write(currentPath, bytes);

            uploadFilePath = uploadFilePath.replace(ApplicationConfig.ROOT_UPLOAD_DIR + File.separator, StringUtils.EMPTY);
            results.add(uploadFilePath);
        }
        return results;
    }

    private static String createPathFromCurrentDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm");
        return simpleDateFormat.format(date);
    }
}
