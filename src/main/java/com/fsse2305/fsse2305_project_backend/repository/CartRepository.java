package com.fsse2305.fsse2305_project_backend.repository;

import com.fsse2305.fsse2305_project_backend.data.cartItem.entity.CartItemEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<CartItemEntity, Integer> {

    @Query(value = "SELECT * FROM cart_item WHERE uid = ?1 AND pid = ?2", nativeQuery = true)
    Optional<CartItemEntity> findProductFromCartByUserAndProductId(Integer uid, Integer pid);

    @Query(value = "SELECT * FROM cart_item WHERE uid = ?1", nativeQuery = true)
    List<CartItemEntity> getUserCartItem(Integer uid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cart_item WHERE uid = ?1 AND pid = ?2", nativeQuery = true)
    void deleteCartItemByUserAndProductId(Integer uid, Integer pid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cart_item WHERE uid = ?1", nativeQuery = true)
    void clearCartByUserId(Integer uid);

}
