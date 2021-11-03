package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhCommentDto;
import com.group7.fruitswebsite.entity.DhComment;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.entity.DhUser;
import com.group7.fruitswebsite.model.DhCommentModel;
import com.group7.fruitswebsite.repository.CommentRepository;
import com.group7.fruitswebsite.repository.ProductRepository;
import com.group7.fruitswebsite.repository.UserRepository;
import com.group7.fruitswebsite.service.CommentService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.DtoUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author duyenthai
 */
@Service
@Log4j
public class CommentServiceImpl implements CommentService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private CommentRepository commentRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse> saveOne(DhCommentModel dhCommentModel) {
        try {
            log.info(dhCommentModel);
            DhComment dhComment = objectMapper.readValue(objectMapper.writeValueAsString(dhCommentModel), DhComment.class);
            dhComment.setCreatedDate(System.currentTimeMillis());
            Optional<DhProduct> currentProduct = productRepository.findById(dhCommentModel.getProductId());
            if (currentProduct.isPresent()) {
                dhComment.setDhProduct(currentProduct.get());
            } else {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.PRODUCT_ID_IS_NOT_DEFINED, HttpStatus.FORBIDDEN);
            }
            checkAndSetUserForComment(dhComment, dhCommentModel);
            commentRepository.save(dhComment);
            log.info(String.format("Save 1 new comment id=%s", dhComment.getId()));
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error("Save 1 new comment error, ", ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> getAllForProductWithPaging(int productId, int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<DhComment> pageComments = commentRepository.findByDhProductId(paging, productId);
            List<DhCommentDto> data = generateDtoToSendApi(pageComments.getContent());
            int totalCommentsForCurrentProduct = commentRepository.findByDhProductId(productId).size();
            ApiResponse.ApiResponseResult apiResponseResult = ApiResponseUtil.mapResultFromList(data, pageComments, totalCommentsForCurrentProduct, size);
            return ApiResponseUtil.getBaseSuccessStatus(apiResponseResult);
        } catch (Exception ex) {
            log.error(String.format("Get all comment for product %s error ", productId), ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    private void checkAndSetUserForComment(DhComment dhComment, DhCommentModel dhCommentModel) {
        if (dhCommentModel.getUserId() != null) {
            Optional<DhUser> optional = userRepository.findById(dhCommentModel.getUserId());
            optional.ifPresent(dhComment::setDhUser);
        } else {
            log.info(String.format("Comment is from a guest, time = %s", new Date(dhComment.getCreatedDate())));
        }
    }

    private List<DhCommentDto> generateDtoToSendApi(List<DhComment> comments) {
        List<DhComment> localComments = new ArrayList<>(comments);
        Collections.sort(localComments);
        // <-- comment_id - comment_dto-->
        Map<Integer, DhCommentDto> result = new HashMap<>();
        List<DhCommentDto> commentDtos = localComments.stream()
                .map(val -> DtoUtil.getCommentDtoFromDhComment(val, objectMapper)).collect(Collectors.toList());
        for (DhCommentDto index : commentDtos) {
            if (index == null) {
                continue;
            }
            if (index.getParentId() == null) {
                result.put(index.getId(), index);
            } else {
                int key = index.getParentId();
                if (result.get(key) != null) {
                    result.get(key).getChildComments().add(index);
                } else {
                    result.put(index.getId(), index);
                }
            }
        }
        return new ArrayList<>(result.values());
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
