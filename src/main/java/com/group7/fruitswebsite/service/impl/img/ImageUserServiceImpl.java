package com.group7.fruitswebsite.service.impl.img;

import com.group7.fruitswebsite.common.ApplicationContextProvider;
import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.entity.DhUser;
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
public class ImageUserServiceImpl implements ImageService<DhUser> {

    private static final String USER_AVATAR_UPLOAD_PATH = ApplicationConfig.ROOT_UPLOAD_DIR + File.separator + ApplicationConfig.USER_AVATAR_RELATIVE_DIR;

    @Override
    public String saveUploadFiles(MultipartFile[] files) {
        return ImageUtil.saveUploadedFiles(files, USER_AVATAR_UPLOAD_PATH);
    }

    @Override
    public List<String> saveUploadedMultiFiles(MultipartFile[] files) {
        return ImageUtil.saveUploadedMultiFiles(files, USER_AVATAR_UPLOAD_PATH);
    }

    @Override
    public Optional<DhUser> checkExists(MultipartFile file, int entityId) {
        try (Session session = ApplicationContextProvider.getApplicationContext().getBean(Session.class)) {
            log.info(file.getOriginalFilename());
            return Optional.ofNullable((DhUser)
                    session.createQuery("from DhUser where avatar like :avatar and id = :entityId")
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
