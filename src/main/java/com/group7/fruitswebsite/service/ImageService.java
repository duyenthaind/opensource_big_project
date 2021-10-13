package com.group7.fruitswebsite.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author duyenthai
 */
public interface ImageService<T> {
    String saveUploadFiles(MultipartFile[] files);
}
