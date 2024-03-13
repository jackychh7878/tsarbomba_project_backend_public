package com.fsse2305.fsse2305_project_backend.service;

import com.fsse2305.fsse2305_project_backend.data.transaction.domainObject.TransactionDetailData;
import com.fsse2305.fsse2305_project_backend.data.transaction.domainObject.TransactionProductEntityData;
import com.fsse2305.fsse2305_project_backend.data.user.domainObject.FirebaseUserData;

import java.util.List;

public interface TransactionService {
    List<TransactionProductEntityData> postTransaction(FirebaseUserData firebaseUserData);

    List<TransactionProductEntityData> getTransaction(FirebaseUserData firebaseUserData, Integer tid);

    TransactionDetailData patchTransaction(FirebaseUserData firebaseUserData, Integer tid);

    List<TransactionProductEntityData> patchTransactionSuccess(FirebaseUserData firebaseUserData, Integer tid);
}
