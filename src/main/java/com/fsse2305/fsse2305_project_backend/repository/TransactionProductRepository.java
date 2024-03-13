package com.fsse2305.fsse2305_project_backend.repository;

import com.fsse2305.fsse2305_project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2305.fsse2305_project_backend.data.transaction.entity.TransactionProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {

    @Query(value = "SELECT * FROM transaction_product tp WHERE tp.tid = ?1", nativeQuery = true)
    List<TransactionProductEntity> findTransactionProductEntityByTransactionID (Integer tid);


}
