package com.group7.fruitswebsite.service.impl;

import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.entity.DhProductImage;
import com.group7.fruitswebsite.service.ImageService;
import com.group7.fruitswebsite.util.ImageUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author duyenthai
 */
public class ImageProductServiceImpl implements ImageService<DhProductImage> {

    private static final String  PRODUCT_UPLOAD_PATH = ApplicationConfig.ROOT_UPLOAD_DIR + File.separator + ApplicationConfig.PRODUCT_UPLOAD_RELATIVE_DIR;

    @Override
    public String saveUploadFiles(MultipartFile[] files) {
        return ImageUtil.saveUploadedFiles(files, PRODUCT_UPLOAD_PATH);
    }
    
    @Override
    public List<String> saveUploadedMultilFiles(MultipartFile[] files) {
        return ImageUtil.saveUploadedMultilFiles(files, PRODUCT_UPLOAD_PATH);
    }
}
