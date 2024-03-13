package com.fsse2305.fsse2305_project_backend.repository;

import com.fsse2305.fsse2305_project_backend.data.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {

    @Query(value = "SELECT t.* FROM transaction t, user u WHERE u.firebase_uid = ?1 AND u.uid = t.uid AND t.tid = ?2", nativeQuery = true)
    Optional<TransactionEntity> findTransactionEntityByUid (String firebaseUid, Integer tid);

}
