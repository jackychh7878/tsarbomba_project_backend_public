package com.fsse2305.fsse2305_project_backend.service;

import com.fsse2305.fsse2305_project_backend.data.product.domainObject.CreateProductData;
import com.fsse2305.fsse2305_project_backend.data.product.domainObject.ProductDetailData;
import com.fsse2305.fsse2305_project_backend.data.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductDetailData createProduct(CreateProductData createProductData);

    List<ProductDetailData> getAllProduct();

    ProductDetailData findProductById(Integer pid);

    ProductEntity findProductEntityById(Integer pid);

    void updateProductStockById(Integer pid, Integer deducedStock);

    List<ProductDetailData> searchProductByKeywords(String searchTerm);
}
