package com.group7.fruitswebsite.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.*;
import com.group7.fruitswebsite.entity.*;
import com.group7.fruitswebsite.repository.OrderProductRepository;
import com.group7.fruitswebsite.repository.ProductImageRepository;
import lombok.extern.log4j.Log4j;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * @author duyenthai
 */
@Log4j
public class DtoUtil {

    private DtoUtil() {

    }

    public static DhUserDto getDtoFromUserDetail(DhUser dhUser, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(dhUser), DhUserDto.class);
        } catch (Exception e) {
            log.error("Map userdto error!", e);
        }
        return null;
    }

    public static DhUserAndRoleDto getDtoFromUserAndRole(DhUser dhUser, ObjectMapper objectMapper) {
        try {
            DhUserDto dhUserDto = objectMapper.readValue(objectMapper.writeValueAsString(dhUser), DhUserDto.class);
            DhUserAndRoleDto dhUserAndRoleDto = objectMapper.readValue(objectMapper.writeValueAsString(dhUserDto), DhUserAndRoleDto.class);
            for (DhRole dhRole : dhUser.getDhRoles()) {
                if (dhRole.getName().equals(Constants.RoleName.SUPER_ADMIN.getName())) {
                    dhUserAndRoleDto.setRole(dhRole.getName());
                    break;
                }
                if (dhRole.getName().equals(Constants.RoleName.ADMIN.getName()) &&
                        (StringUtils.isEmpty(dhUserAndRoleDto.getRole()) || dhUserAndRoleDto.getRole().equals(Constants.RoleName.USER.getName()))) {
                    dhUserAndRoleDto.setRole(dhRole.getName());
                    continue;
                }
                dhUserAndRoleDto.setRole(dhRole.getName());
            }
            return dhUserAndRoleDto;

        } catch (Exception e) {
            log.error("Map userAndRole dto error!", e);
        }
        return null;
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
            productDto.setSalePercent();
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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
            DhBlogDto dto = objectMapper.readValue(objectMapper.writeValueAsString(dhBlog), DhBlogDto.class);
            if (dhBlog.getCreatedDate() != null && dhBlog.getCreatedDate() > 0) {
                dto.setDate(simpleDateFormat.format(new Date(dhBlog.getCreatedDate())));
            }
            return dto;
        } catch (Exception ex) {
            log.error("Map blog to dto error, ", ex);
        }
        return null;
    }

    public static DhCommentDto getCommentDtoFromDhComment(DhComment dhComment, ObjectMapper objectMapper) {
        try {
            DhCommentDto dto = objectMapper.readValue(objectMapper.writeValueAsString(dhComment), DhCommentDto.class);
            dto.setProductId(dhComment.getDhProduct() != null ? dhComment.getDhProduct().getId() : null);
            dto.setDisplayName(dhComment.getDhUser() != null ? dhComment.getDhUser().getName() : "Guest");
            return dto;
        } catch (Exception ex) {
            log.error("Map comment to dto error, ", ex);
        }
        return null;
    }

    public static DhCartDto getCartDtoFromDhCart(DhCart dhCart, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(dhCart), DhCartDto.class);
        } catch (Exception ex) {
            log.error("Map cart to dto error, ", ex);
        }
        return null;
    }

    public static DhOrderDto getOrderDtoFromDhOrder(DhOrder dhOrder, ObjectMapper objectMapper, OrderProductRepository orderProductRepository) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DhOrderDto dhOrderDto = objectMapper.readValue(objectMapper.writeValueAsString(dhOrder), DhOrderDto.class);
            dhOrderDto.setCouponId(Objects.nonNull(dhOrder.getDhCoupon()) ? dhOrder.getDhCoupon().getId() : null);
            dhOrderDto.setUserId(Objects.nonNull(dhOrder.getDhUser()) ? dhOrder.getDhUser().getId() : null);
            dhOrderDto.setDate(simpleDateFormat.format(new Date(dhOrder.getCreatedDate())));
            if (Objects.nonNull(orderProductRepository)) {
                List<DhOrderProduct> listAllProducts = orderProductRepository.findByOrderId(dhOrder.getId());
                for (DhOrderProduct index : listAllProducts) {
                    DhOrderProductDto dto = objectMapper.readValue(objectMapper.writeValueAsString(index), DhOrderProductDto.class);
                    dto.setOrder(dhOrder.getId());
                    dhOrderDto.getListProductDto().add(dto);
                }
            }
            return dhOrderDto;
        } catch (Exception ex) {
            log.error("Map order to dto error, ", ex);
        }
        return null;
    }
}
