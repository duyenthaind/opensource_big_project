package com.group7.fruitswebsite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author duyenthai
 */
public interface ImageService<T> {
    String saveUploadFiles(MultipartFile[] files);
    List<String> saveUploadedMultiFiles(MultipartFile[] files);
    Optional<T> checkExists(MultipartFile file);
}
