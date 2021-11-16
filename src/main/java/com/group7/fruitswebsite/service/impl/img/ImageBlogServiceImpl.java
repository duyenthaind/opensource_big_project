package com.group7.fruitswebsite.service.impl.img;

import com.group7.fruitswebsite.common.ApplicationContextProvider;
import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.entity.DhBlog;
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
public class ImageBlogServiceImpl implements ImageService<DhBlog> {

    private static final String BLOG_UPLOAD_PATH = ApplicationConfig.ROOT_UPLOAD_DIR + File.separator + ApplicationConfig.BLOG_UPLOAD_RELATIVE_DIR;

    @Override
    public String saveUploadFiles(MultipartFile[] files) {
        return ImageUtil.saveUploadedFiles(files, BLOG_UPLOAD_PATH);
    }

    @Override
    public List<String> saveUploadedMultiFiles(MultipartFile[] files) {
        return ImageUtil.saveUploadedMultiFiles(files, BLOG_UPLOAD_PATH);
    }

    @Override
    public Optional<DhBlog> checkExists(MultipartFile file, int entityId) {
        try (Session session = ApplicationContextProvider.getApplicationContext().getBean(Session.class)) {
            return Optional.ofNullable((DhBlog)
                    session.createQuery("from DhBlog  where avatar like :avatar and id = :entityId")
                            .setParameter("entityId", entityId)
                            .setParameter("avatar", String.format("%%%s%%", file.getOriginalFilename()))
                            .uniqueResult()
            );
        } catch (Exception ex) {
            log.error(String.format("Check exists file error for entity id %s, ", entityId), ex);
        }
        return Optional.empty();
    }
}
