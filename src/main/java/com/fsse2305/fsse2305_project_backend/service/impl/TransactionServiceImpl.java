package com.fsse2305.fsse2305_project_backend.service.impl;

import com.fsse2305.fsse2305_project_backend.data.cartItem.domainObject.CartItemDetailData;
import com.fsse2305.fsse2305_project_backend.data.transaction.domainObject.TransactionDetailData;
import com.fsse2305.fsse2305_project_backend.data.transaction.domainObject.TransactionProductEntityData;
import com.fsse2305.fsse2305_project_backend.data.transaction.enumfolder.Status;
import com.fsse2305.fsse2305_project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2305.fsse2305_project_backend.data.transaction.entity.TransactionProductEntity;
import com.fsse2305.fsse2305_project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2305.fsse2305_project_backend.data.user.entity.UserEntity;
import com.fsse2305.fsse2305_project_backend.exception.product.NoStockException;
import com.fsse2305.fsse2305_project_backend.exception.transaction.CompleteTransactionException;
import com.fsse2305.fsse2305_project_backend.exception.transaction.GetTransactionDetailsException;
import com.fsse2305.fsse2305_project_backend.exception.transaction.GetTransactionProductDetailsException;
import com.fsse2305.fsse2305_project_backend.exception.transaction.UpdateTransactionDetailsException;
import com.fsse2305.fsse2305_project_backend.repository.TransactionProductRepository;
import com.fsse2305.fsse2305_project_backend.repository.TransactionRepository;
import com.fsse2305.fsse2305_project_backend.service.CartItemService;
import com.fsse2305.fsse2305_project_backend.service.ProductService;
import com.fsse2305.fsse2305_project_backend.service.TransactionService;
import com.fsse2305.fsse2305_project_backend.service.UserService;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    Logger logger = LoggerFactory.getLogger(getClass());
    String warnTitle, warnReason;
    private final TransactionRepository transactionRepository;
    private final TransactionProductRepository transactionProductRepository;
    private final UserService userService;
    private final CartItemService cartItemService;
    private final ProductService productService;
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionProductRepository transactionProductRepository, UserService userService, CartItemService cartItemService, ProductService productService) {
        this.transactionRepository = transactionRepository;
        this.transactionProductRepository = transactionProductRepository;
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public List<TransactionProductEntityData> postTransaction(FirebaseUserData firebaseUserData){
        UserEntity loginUser = getUserEntity(firebaseUserData);
        BigDecimal totalSum = BigDecimal.valueOf(0);
        BigDecimal subTotal;

        for (CartItemDetailData data: cartItemService.getUserCart(firebaseUserData)){
            subTotal = data.getProduct().getPrice().multiply(BigDecimal.valueOf(data.getQuantity()));
            totalSum = totalSum.add(subTotal);
        }
        TransactionEntity transactionEntity = new TransactionEntity(loginUser,Status.PREPARE, totalSum);
        transactionRepository.save(transactionEntity);

        List<TransactionProductEntityData> transactionProductEntityList = new ArrayList<>();
        for (CartItemDetailData data: cartItemService.getUserCart(firebaseUserData)){
            TransactionProductEntity transactionProductEntity = new TransactionProductEntity(transactionEntity, data);
            transactionProductRepository.save(transactionProductEntity);
            transactionProductEntityList.add(new TransactionProductEntityData(transactionProductEntity));
        }
        return transactionProductEntityList;
    }

    @Override
    public List<TransactionProductEntityData> getTransaction(FirebaseUserData firebaseUserData, Integer tid){
        UserEntity loginUser = getUserEntity(firebaseUserData);
        warnTitle = "GetTransactionByIdFailed: ";
        try {
            if (tid == null) {
                warnReason = "Attribute is null";
                throw new GetTransactionDetailsException();
            }
            Optional<TransactionEntity> transactionEntity = transactionRepository.findTransactionEntityByUid(loginUser.getFirebaseUid(), tid);

            if (transactionEntity.isEmpty()) {
                warnReason = "Transaction not found tid: " + tid;
                throw new GetTransactionDetailsException();
            }
            List<TransactionProductEntity> transactionProductEntityList = transactionProductRepository.findTransactionProductEntityByTransactionID(transactionEntity.get().getTid());
            if (transactionProductEntityList.isEmpty()){
                warnReason = "Get transaction product details failed";
                throw new GetTransactionProductDetailsException();
            }
            List<TransactionProductEntityData> transactionProductEntityDataList = new ArrayList<>();
            for (TransactionProductEntity entity: transactionProductEntityList){
                TransactionProductEntityData data = new TransactionProductEntityData(entity);
                transactionProductEntityDataList.add(data);
            }
            return transactionProductEntityDataList;
        } catch (GetTransactionDetailsException ex){
            logger.warn(warnTitle + warnReason);
            throw new GetTransactionDetailsException(warnTitle + warnReason);
        } catch (GetTransactionProductDetailsException ex){
            logger.warn(warnTitle + warnReason);
            throw new GetTransactionProductDetailsException(warnTitle + warnReason);
        }
    }

    @Transactional
    @Override
    public TransactionDetailData patchTransaction(FirebaseUserData firebaseUserData, Integer tid){
        UserEntity loginUser = getUserEntity(firebaseUserData);
        warnTitle = "UpdateTransactionByIdFailed: ";
        try{
            if (tid == null) {
                warnReason = "Attribute is null";
                throw new UpdateTransactionDetailsException();
            }
            Optional<TransactionEntity> transactionEntity = transactionRepository.findTransactionEntityByUid(loginUser.getFirebaseUid(), tid);

            if (transactionEntity.isEmpty()) {
                warnReason = "Transaction not found tid: " + tid;
                throw new UpdateTransactionDetailsException();
            }

            //Invalid Status Exception
            if (transactionEntity.get().getStatus().equals(Status.PROCESSING)){
                warnReason = "Transaction already processing tid: " + tid;
                throw new UpdateTransactionDetailsException();
            }
            if (transactionEntity.get().getStatus().equals(Status.SUCCESS)){
                warnReason = "Transaction already completed tid: " + tid;
                throw new UpdateTransactionDetailsException();
            }
            transactionEntity.get().setStatus(Status.PROCESSING);

            //Update the stock in product table
            for (TransactionProductEntity entity: transactionEntity.get().getTransactionProductEntityList()){
                productService.updateProductStockById(entity.getPid(), entity.getQuantity());
            }
            transactionRepository.save(transactionEntity.get());
            return new TransactionDetailData(transactionEntity.get());
        } catch (UpdateTransactionDetailsException ex){
            logger.warn(warnTitle + warnReason);
            throw new UpdateTransactionDetailsException(warnTitle + warnReason);
        } catch (NoStockException ex){
            warnReason = "Out of stock";
            logger.warn(warnTitle + warnReason);
            throw new NoStockException(warnTitle + warnReason);
        }
    }

    @Transactional
    @Override
    public List<TransactionProductEntityData> patchTransactionSuccess(FirebaseUserData firebaseUserData, Integer tid){
        UserEntity loginUser = getUserEntity(firebaseUserData);
        warnTitle = "CompleteTransactionFailed tid: " + tid;
        try{
            if (tid == null) {
                warnReason = "Attribute is null";
                throw new CompleteTransactionException();
            }
            Optional<TransactionEntity> transactionEntity = transactionRepository.findTransactionEntityByUid(loginUser.getFirebaseUid(), tid);
            if (transactionEntity.isEmpty()) {
                warnReason = "Transaction not found tid: " + tid;
                throw new CompleteTransactionException();
            }
            List<TransactionProductEntity> transactionProductEntityList = transactionProductRepository.findTransactionProductEntityByTransactionID(transactionEntity.get().getTid());
            if (transactionProductEntityList.isEmpty()){
                warnReason = "Get transaction product details failed";
                throw new CompleteTransactionException();
            }

            //Check the status to SUCCESS
            if (transactionEntity.get().getStatus().equals(Status.PREPARE)){
                warnReason = "Transaction is not paid yet tid: " + tid;
                throw new CompleteTransactionException();
            }
            if (transactionEntity.get().getStatus().equals(Status.SUCCESS)){
                warnReason = "Transaction already completed in the past tid: " + tid;
                throw new CompleteTransactionException();
            }
            transactionEntity.get().setStatus(Status.SUCCESS);
            transactionRepository.save(transactionEntity.get());

            List<TransactionProductEntityData> transactionProductEntityDataList = new ArrayList<>();
            for (TransactionProductEntity entity: transactionProductEntityList){
                transactionProductEntityDataList.add(new TransactionProductEntityData(entity));
            }
            //Clear the cart item
            cartItemService.clearCartItemByUid(firebaseUserData, loginUser.getUid());

            return transactionProductEntityDataList;

        } catch (CompleteTransactionException ex){
            logger.warn(warnTitle + warnReason);
            throw new CompleteTransactionException(warnTitle + warnReason);
        }
    }


    public UserEntity getUserEntity(FirebaseUserData firebaseUserData){
        return userService.getEntityByFirebaseUserData(firebaseUserData);
    }
}
