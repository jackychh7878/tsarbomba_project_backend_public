package com.fsse2305.fsse2305_project_backend.repository;

import com.fsse2305.fsse2305_project_backend.data.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository <ProductEntity, Integer> {

    @Query(value = "SELECT * FROM product p WHERE p.name = ?1", nativeQuery = true)
    Optional<ProductEntity> findByProductName (String productName);

    @Query(value = "SELECT * FROM product p", nativeQuery = true)
    List<ProductEntity> getAllProduct();

    @Query(value = "SELECT * FROM product p WHERE p.pid = ?1", nativeQuery = true)
    Optional<ProductEntity> findByProductID (Integer productID);

    @Query(value = "SELECT * FROM product p WHERE p.name LIKE %?1%", nativeQuery = true)
    List<ProductEntity> searchProductEntityByNameLike(String searchTerm);

}
