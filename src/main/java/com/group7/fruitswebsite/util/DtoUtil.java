package com.group7.fruitswebsite.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.dto.DhProductDto;
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

    private  DtoUtil(){

    }

    public static DhProductDto getDtoFromProduct(DhProduct dhProduct, ObjectMapper objectMapper, ProductImageRepository productImageRepository)  {
        try{
            DhProductDto productDto = objectMapper.readValue(objectMapper.writeValueAsString(dhProduct), DhProductDto.class);
            if (dhProduct.getCategory() != null) {
                productDto.setCategoryId(dhProduct.getCategory().getId());
                productDto.setCategory(dhProduct.getCategory());
            }
            List<DhProductImage> listCurrentProductImage = productImageRepository.getByDhProductId(dhProduct.getId());
            if (!listCurrentProductImage.isEmpty()) {
                productDto.setProductImages(listCurrentProductImage.stream().map(DhProductImage::getPath).collect(Collectors.toList()));
            } else {
                productDto.setProductImages(Collections.emptyList());
            }
            return productDto;
        } catch (Exception ex){
            log.error("Map error, ", ex);
        }
        return null;
    }
}
