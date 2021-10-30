package com.group7.fruitswebsite.service.image.impl;

import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.entity.DhCategory;
import com.group7.fruitswebsite.service.ImageService;
import com.group7.fruitswebsite.util.ImageUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * @author duyenthai
 */
public class ImageCategoryServiceImpl implements ImageService<DhCategory> {

    private static final String  CATEGORY_UPLOAD_PATH = ApplicationConfig.ROOT_UPLOAD_DIR + File.separator + ApplicationConfig.CATEGORY_UPLOAD_RELATIVE_DIR;

    @Override
    public String saveUploadFiles(MultipartFile[] files) {
        return ImageUtil.saveUploadedFiles(files, CATEGORY_UPLOAD_PATH);
    }

	@Override
	public List<String> saveUploadedMultiFiles(MultipartFile[] files) {
		return ImageUtil.saveUploadedMultiFiles(files, CATEGORY_UPLOAD_PATH);
	}

    @Override
    public Optional<DhCategory> checkExists(MultipartFile file, int entityId) {
        return Optional.empty();
    }
}
