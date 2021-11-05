package com.group7.fruitswebsite.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.dto.ApiResponse;
import com.group7.fruitswebsite.dto.DhProductDto;
import com.group7.fruitswebsite.dto.search.ProductCondition;
import com.group7.fruitswebsite.entity.DhCategory;
import com.group7.fruitswebsite.entity.DhProduct;
import com.group7.fruitswebsite.entity.DhProductImage;
import com.group7.fruitswebsite.model.DhProductModel;
import com.group7.fruitswebsite.service.ProductImageService;
import com.group7.fruitswebsite.service.search.ProductSearchService;
import com.group7.fruitswebsite.util.ApiResponseUtil;
import com.group7.fruitswebsite.util.DateUtil;
import com.group7.fruitswebsite.util.DtoUtil;
import com.group7.fruitswebsite.util.StringUtil;

import lombok.extern.log4j.Log4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.group7.fruitswebsite.repository.CategoryRepository;
import com.group7.fruitswebsite.repository.ProductImageRepository;
import com.group7.fruitswebsite.repository.ProductRepository;
import com.group7.fruitswebsite.service.ProductService;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductImageRepository productImageRepository;
    private ProductImageService productImageService;
    private ProductSearchService productSearchService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DhProduct setNewProduct(DhProductModel dhProductModel) throws JsonProcessingException {
        String modelJson = objectMapper.writeValueAsString(dhProductModel);
        DhProduct dhProduct = objectMapper.readValue(modelJson, DhProduct.class);

        Optional<DhCategory> optionalDhCategory = categoryRepository.findById(dhProductModel.getCategoryId());
        optionalDhCategory.ifPresent(dhProduct::setCategory);

        dhProduct.setCreatedDate(System.currentTimeMillis());
        dhProduct.setSeo(StringUtil.seo(dhProductModel.getProductName()) + "-" + System.currentTimeMillis());

        return dhProduct;
    }

    public DhProduct mapProductFromModel(DhProductModel dhProductModel) throws JsonProcessingException {
        String modelJson = objectMapper.writeValueAsString(dhProductModel);
        DhProduct dhProduct = objectMapper.readValue(modelJson, DhProduct.class);

        Optional<DhCategory> optionalDhCategory = categoryRepository.findById(dhProductModel.getCategoryId());
        optionalDhCategory.ifPresent(dhProduct::setCategory);

        dhProduct.setUpdatedDate(System.currentTimeMillis());

        return dhProduct;
    }

    private void saveImageProduct(DhProductModel dhProductModel, DhProduct dhProduct) {
        if (dhProductModel.getPathUploadedAvatar() != null && !dhProductModel.getPathUploadedAvatar().isEmpty()) {
            List<String> imagePath = dhProductModel.getPathUploadedAvatar();
            for (int i = 0; i < dhProductModel.getFiles().length; i++) {
                DhProductImage dhProductImage = new DhProductImage();
                dhProductImage.setCreatedBy(Constants.SystemUser.SYSTEM_USER_ID);
                dhProductImage.setCreatedDate(System.currentTimeMillis());
                dhProductImage.setName(dhProductModel.getFiles()[i].getName());
                dhProductImage.setPath(imagePath.get(i).replace(ApplicationConfig.ROOT_UPLOAD_DIR + File.separator, StringUtils.EMPTY));
                dhProductImage.setDhProduct(dhProduct);
                productImageRepository.save(dhProductImage);
                dhProductModel.addProductImages(dhProductImage);
            }
        }
    }

    private void dropOldImageProduct(DhProductModel dhProductModel, DhProduct dhProduct) {
        if (dhProductModel.getDhProductImages().isEmpty()) {
            return;
        }
        productImageService.deleteOldImageFromProduct(dhProductModel.getDhProductImages(), dhProduct.getId());
    }


    public ResponseEntity<ApiResponse> saveOne(DhProductModel dhProductModel) {
        try {
            DhProduct dhProduct = setNewProduct(dhProductModel);
            if (dhProduct.getCategory() == null) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.NOT_FOUND_CATEGORY, HttpStatus.EXPECTATION_FAILED);
            }
            productRepository.save(dhProduct);
            saveImageProduct(dhProductModel, dhProduct);
            log.info(String.format("Save 1 new product, id=%d", dhProduct.getId()));
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error("Error insert new product, ", ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAll() {
        try {
            List<DhProductDto> listAllProducts = getAllProductsAsDto();
            ApiResponse.ApiResponseResult apiResponseResult = new ApiResponse.ApiResponseResult();
            apiResponseResult.setData(listAllProducts);
            return ApiResponseUtil.getBaseSuccessStatus(apiResponseResult);

        } catch (Exception ex) {
            log.error("Error get all product, ", ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public List<DhProductDto> getAllProductsAsDto() {
        try {
            List<DhProductDto> result = new ArrayList<>();
            List<DhProduct> listAllProducts = productRepository.findAll();
            for (DhProduct dhProduct : listAllProducts) {
                result.add(DtoUtil.getDtoFromProduct(dhProduct, objectMapper, productImageRepository));
            }
            return result;
        } catch (Exception ex) {
            log.error("Get all product as dto error, ", ex);
        }
        return Collections.emptyList();
    }

    @Override
    public List<DhProductDto> getTopRandomProductsAsDto(int limit) {
        try {
            List<DhProductDto> result = new ArrayList<>();
            List<DhProduct> listTop9Products = productRepository.getTopRandom(limit);
            for (DhProduct dhProduct : listTop9Products) {
                result.add(DtoUtil.getDtoFromProduct(dhProduct, objectMapper, productImageRepository));
            }
            return result;
        } catch (Exception ex) {
            log.error("Get product top random as dto error, ", ex);
        }
        return Collections.emptyList();
    }

    @Override
    public List<DhProductDto> getProductsInListCategoryAsDto(int total) {
        try {
            List<DhProductDto> result = new ArrayList<>();
            List<DhProduct> listProductsInListCate = productRepository.getProductByListCategoryId(categoryRepository.findAllId(), total);
            for (DhProduct dhProduct : listProductsInListCate) {
                result.add(DtoUtil.getDtoFromProduct(dhProduct, objectMapper, productImageRepository));
            }
            return result;
        } catch (Exception ex) {
            log.error("Get product inlist category as dto error, ", ex);
        }
        return Collections.emptyList();
    }

    @Override
    public List<DhProductDto> getProductsOrderByPriceSaleAscAsDto() {
        try {
            List<DhProductDto> result = new ArrayList<>();
            List<DhProduct> listProducts = productRepository.getProductsOrderByPriceSaleAsc();
            for (DhProduct dhProduct : listProducts) {
                result.add(DtoUtil.getDtoFromProduct(dhProduct, objectMapper, productImageRepository));
            }
            return result;
        } catch (Exception ex) {
            log.error("Get product order by price sale as dto error, ", ex);
        }
        return Collections.emptyList();
    }

    @Override
    public List<DhProductDto> getProductsByCategoryIdWithPaging(int page, int size, Integer categoryId) {
        try {
            Pageable paging = PageRequest.of(page, size);
            List<DhProductDto> result = new ArrayList<>();
            Page<DhProduct> pageProducts = productRepository.findByCategory(paging, categoryRepository.getById(categoryId));
            for (DhProduct dhProduct : pageProducts) {
                result.add(DtoUtil.getDtoFromProduct(dhProduct, objectMapper, productImageRepository));
            }
            return result;
        } catch (Exception ex) {
            log.error("Get product order by price sale as dto error, ", ex);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Integer> getTotalPagesByCategory(int size, int categoryId) {
        List<Integer> arrayTotalPage = new ArrayList<Integer>();
        int totalProductByCategory = productRepository.findByCategory(categoryId);
        int totalPages = totalProductByCategory % size == 0 ? totalProductByCategory / size : totalProductByCategory / size + 1;

        arrayTotalPage.add(0);
        if (totalPages >= 2) {
            for (int i = 1; i < totalPages; i++) {
                arrayTotalPage.add(i);
            }
        }
        return arrayTotalPage;
    }

    @Override
    public ResponseEntity<ApiResponse> search(List<ProductCondition> conditions) {
        try {
            List<DhProduct> searchedProducts = productSearchService.search(conditions);
            List<DhProductDto> productDtos = searchedProducts.stream()
                    .map(val -> DtoUtil.getDtoFromProduct(val, objectMapper, productImageRepository)).collect(Collectors.toList());
            ApiResponse.ApiResponseResult responseResult = ApiResponseUtil.mapResultWithoutPaging(productDtos);
            return ApiResponseUtil.getBaseSuccessStatus(responseResult);
        } catch (Exception ex) {
            log.error(String.format("Search product with conditions %s error", conditions), ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public DhProductDto getOneProductsAsDto(Integer id) {
        try {
            Optional<DhProduct> optional = productRepository.findById(id);
            DhProduct dhProduct = optional.orElseGet(DhProduct::new);
            return DtoUtil.getDtoFromProduct(dhProduct, objectMapper, productImageRepository);
        } catch (Exception ex) {
            log.error("Get one product as dto error, ", ex);
        }
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> getAllWithPaging(int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<DhProduct> pageProducts = productRepository.findAll(paging);
            List<DhProductDto> productDtos = pageProducts.getContent().stream()
                    .map(val -> DtoUtil.getDtoFromProduct(val, objectMapper, productImageRepository)).collect(Collectors.toList());
            ApiResponse.ApiResponseResult responseResult = new ApiResponse.ApiResponseResult();
            int totalProducts = productRepository.findAll().size();
            int totalPages = totalProducts % size == 0 ? totalProducts / size : totalProducts / size + 1;
            responseResult.setData(productDtos);
            responseResult.setPage(pageProducts.getNumber() + 1);
            responseResult.setPerPage(pageProducts.getNumberOfElements());
            responseResult.setTotalPages(totalPages);
            responseResult.setTotal(productDtos.size());
            return ApiResponseUtil.getBaseSuccessStatus(responseResult);
        } catch (Exception ex) {
            log.error("Get all products with paging error ", ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getOne(int id) {
        try {
            Optional<DhProduct> optional = productRepository.findById(id);
            if (optional.isPresent()) {
                DhProduct product = optional.get();
                DhProductDto dto = DtoUtil.getDtoFromProduct(product, objectMapper, productImageRepository);
                if (dto != null) {
                    dto.setCategoryId(product.getCategory() == null ? -1 : product.getCategory().getId());
                }
                ApiResponse.ApiResponseResult apiResponseResult = new ApiResponse.ApiResponseResult();
                apiResponseResult.setData(Collections.singletonList(dto));
                return ApiResponseUtil.getBaseSuccessStatus(apiResponseResult);
            }
            return ResponseEntity.ok(new ApiResponse());
        } catch (Exception ex) {
            log.error(String.format("Get one product with id %s error", id), ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> update(DhProductModel dhProductModel) {
        try {
            DhProduct dhProduct = mapProductFromModel(dhProductModel);
            dhProduct.setSeo(StringUtil.seo(dhProductModel.getProductName()) + "-" + System.currentTimeMillis());
            if (dhProduct.getCategory() == null) {
                return ApiResponseUtil.getCustomStatusWithMessage(Constants.ApiMessage.NOT_FOUND_CATEGORY, HttpStatus.EXPECTATION_FAILED);
            }
            productRepository.save(dhProduct);
            saveImageProduct(dhProductModel, dhProduct);
            dropOldImageProduct(dhProductModel, dhProduct);

            log.info(String.format("update 1 product, id=%d", dhProduct.getId()));
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error(String.format("Update product with id %s error , ", dhProductModel.getId()), ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> delete(int id) {
        try {
            productImageRepository.deleteByDhProductId(id);
            log.info(String.format("Delete all product image with product_id=%s", id));
            productRepository.deleteById(id);
            log.info(String.format("Delete 1 product, id=%d", id));
            return ApiResponseUtil.getBaseSuccessStatus(null);
        } catch (Exception ex) {
            log.error(String.format("Delete product with id %s error , ", id), ex);
            return ApiResponseUtil.getBaseFailureStatus();
        }
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setProductImageRepository(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @Autowired
    public void setProductImageService(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @Autowired
    public void setProductSearchService(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }
}
