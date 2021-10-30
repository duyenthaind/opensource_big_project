package com.group7.fruitswebsite.service.image.impl;

import com.group7.fruitswebsite.common.ApplicationContextProvider;
import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.entity.DhProductImage;
import com.group7.fruitswebsite.service.ImageService;
import com.group7.fruitswebsite.util.ImageUtil;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * @author duyenthai
 */
@Log4j
public class ImageProductServiceImpl implements ImageService<DhProductImage> {

    private static final String PRODUCT_UPLOAD_PATH = ApplicationConfig.ROOT_UPLOAD_DIR + File.separator + ApplicationConfig.PRODUCT_UPLOAD_RELATIVE_DIR;

    @Override
    public String saveUploadFiles(MultipartFile[] files) {
        return ImageUtil.saveUploadedFiles(files, PRODUCT_UPLOAD_PATH);
    }

    @Override
    public List<String> saveUploadedMultiFiles(MultipartFile[] files) {
        return ImageUtil.saveUploadedMultiFiles(files, PRODUCT_UPLOAD_PATH);
    }

    @Override
    public Optional<DhProductImage> checkExists(MultipartFile file, int entityId) {
        try (Session session = ApplicationContextProvider.getApplicationContext().getBean(Session.class)) {
            return Optional.ofNullable((DhProductImage)
                    session.createQuery("from DhProductImage where name = :name and dhProduct.id=:entityId")
                            .setParameter("name", "%" + file.getName())
                            .setParameter("entityId", entityId)
                            .uniqueResult()
            );
        } catch (Exception ex) {
            log.error("Check exists file error, ", ex);
        }
        return Optional.empty();
    }
}
