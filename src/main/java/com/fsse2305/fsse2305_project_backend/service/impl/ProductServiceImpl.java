package com.fsse2305.fsse2305_project_backend.service.impl;

import com.fsse2305.fsse2305_project_backend.data.product.domainObject.CreateProductData;
import com.fsse2305.fsse2305_project_backend.data.product.domainObject.ProductDetailData;
import com.fsse2305.fsse2305_project_backend.data.product.entity.ProductEntity;
import com.fsse2305.fsse2305_project_backend.exception.product.CreateProductException;
import com.fsse2305.fsse2305_project_backend.exception.product.NoStockException;
import com.fsse2305.fsse2305_project_backend.exception.product.ProductNotFoundException;
import com.fsse2305.fsse2305_project_backend.repository.ProductRepository;
import com.fsse2305.fsse2305_project_backend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(getClass());
    String warnTitle, warnReason;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDetailData createProduct(CreateProductData createProductData){
        try{
            if ((createProductData.getName() == null) ||
                    (createProductData.getPrice() == null) ||
                    (createProductData.getStock() == null)){
                warnReason = "Attribute is null";
                throw new CreateProductException();
            }

            if (createProductData.getStock() < 0){
                warnReason = "Number of stock cannot be negative";
                throw new CreateProductException();
            }

            if (productRepository.findByProductName(createProductData.getName()).isPresent()){
                warnReason = "Product existed";
                throw new CreateProductException();
            }
            ProductEntity product = new ProductEntity(createProductData);
            productRepository.save(product);
            return new ProductDetailData(product);
        } catch (CreateProductException ex){
            warnTitle = "CreateProductFailed: ";
            if (warnReason != null){
                logger.warn(warnTitle + warnReason);
                throw new CreateProductException(warnTitle + warnReason);
            }
            throw ex;
        }  catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<ProductDetailData> getAllProduct(){
        List<ProductDetailData> productDetailDataList = new ArrayList<>();
        for (ProductEntity product: productRepository.getAllProduct()){
            productDetailDataList.add(new ProductDetailData(product));
        }
        return productDetailDataList;
    }

    @Override
    public ProductDetailData findProductById(Integer pid){
        try{
            if (pid == null){
                warnReason = "Product ID is null";
                throw new ProductNotFoundException();
            }
            Optional<ProductEntity> product = productRepository.findByProductID(pid);
            if (product.isEmpty()){
                warnReason = "Product not found";
                throw new ProductNotFoundException();
            } else {
                return new ProductDetailData(product.get());
            }
        } catch (ProductNotFoundException ex){
            warnTitle = "FindProduct Failed: ";
            if (warnReason != null){
                logger.warn(warnTitle + warnReason);
                throw new ProductNotFoundException(warnTitle + warnReason);
            }
            throw ex;
        } catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ProductEntity findProductEntityById(Integer pid){
        try{
            if (pid == null){
                throw new ProductNotFoundException();
            }
            Optional<ProductEntity> product = productRepository.findByProductID(pid);
            if (product.isEmpty()){
                throw new ProductNotFoundException();
            } else {
                return product.get();
            }
        } catch (ProductNotFoundException ex){
            throw ex;
        } catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public void updateProductStockById(Integer pid, Integer deducedStock){
        try {
            if (pid == null) {
                throw new ProductNotFoundException();
            }
            Optional<ProductEntity> product = productRepository.findByProductID(pid);
            if (product.isEmpty()){
                throw new ProductNotFoundException();
            }
            if (product.get().getStock() < deducedStock){
                throw new NoStockException();
            }
            product.get().setStock(product.get().getStock() - deducedStock);
            productRepository.save(product.get());
        } catch (ProductNotFoundException ex){
            throw ex;
        } catch (NoStockException ex){
            throw ex;
        } catch (Exception ex){
            throw ex;
        }
    }

//    Bonus search product by keywords
    @Override
    public List<ProductDetailData> searchProductByKeywords(String searchTerm){
        List<ProductDetailData> productDetailDataList = new ArrayList<>();
        for (ProductEntity entity: productRepository.searchProductEntityByNameLike(searchTerm)){
            productDetailDataList.add(new ProductDetailData(entity));
        }
        return productDetailDataList;
    }


}
