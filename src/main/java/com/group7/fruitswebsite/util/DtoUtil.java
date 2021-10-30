package com.group7.fruitswebsite.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.dto.DhBlogDto;
import com.group7.fruitswebsite.dto.DhProductDto;
import com.group7.fruitswebsite.dto.DhProductImageDto;
import com.group7.fruitswebsite.entity.DhBlog;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.entity.DhProductImage;
import com.group7.fruitswebsite.repository.ProductImageRepository;
import lombok.extern.log4j.Log4j;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author duyenthai
 */
@Log4j
public class DtoUtil {

    private DtoUtil() {

    }

    public static DhProductDto getDtoFromProduct(DhProduct dhProduct, ObjectMapper objectMapper, ProductImageRepository productImageRepository) {
        try {
            DhProductDto productDto = objectMapper.readValue(objectMapper.writeValueAsString(dhProduct), DhProductDto.class);
            if (dhProduct.getCategory() != null) {
                productDto.setCategoryId(dhProduct.getCategory().getId());
                productDto.setCategory(dhProduct.getCategory());
            }
            List<DhProductImage> listCurrentProductImage = productImageRepository.getByDhProductId(dhProduct.getId());
            if (!listCurrentProductImage.isEmpty()) {
                productDto.setProductImages(listCurrentProductImage.stream().map(DhProductImage::getPath).collect(Collectors.toList()));
                List<DhProductImageDto> listProductImageDtos = listCurrentProductImage.stream().map(val -> getProductImageDtoFromProduct(val, objectMapper)).collect(Collectors.toList());
                productDto.setListProductImages(listProductImageDtos);
            } else {
                productDto.setProductImages(Collections.emptyList());
                productDto.setListProductImages(Collections.emptyList());
            }
            return productDto;
        } catch (Exception ex) {
            log.error("Map error, ", ex);
        }
        return null;
    }

    public static DhProductImageDto getProductImageDtoFromProduct(DhProductImage dhProductImage, ObjectMapper objectMapper) {
        try {
            DhProductImageDto productImageDto = objectMapper.readValue(objectMapper.writeValueAsString(dhProductImage), DhProductImageDto.class);
            productImageDto.setProductId(dhProductImage.getDhProduct() == null ? -1 : dhProductImage.getDhProduct().getId());
            return productImageDto;
        } catch (Exception ex) {
            log.error("Map error, ex");
        }
        return null;
    }

    public static DhBlogDto getBlogDtoFromDhBlog(DhBlog dhBlog, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(dhBlog), DhBlogDto.class);
        } catch (Exception ex) {
            log.error("Map blog to dto error, ", ex);
        }
        return null;
    }
}
