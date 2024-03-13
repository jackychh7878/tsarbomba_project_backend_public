package com.fsse2305.fsse2305_project_backend.data.user.entity;

import com.fsse2305.fsse2305_project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2305.fsse2305_project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2305.fsse2305_project_backend.data.user.domainObject.FirebaseUserData;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user", indexes = {
    @Index(name = "idx_firebase_uid", columnList = "firebase_uid")
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    @Column(name = "firebase_uid", unique = true, nullable = false)
    private String firebaseUid;
    @Column(nullable = false)
    private String email;
    @OneToMany(mappedBy = "user")
    @Column(nullable = false)
    private List<CartItemEntity> cartItemOwning = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Column(nullable = false)
    private List<TransactionEntity> transactionOwning = new ArrayList<>();


    public UserEntity() {
    }

    public UserEntity(FirebaseUserData firebaseUserData) {
        this.firebaseUid = firebaseUserData.getFirebaseUid();
        this.email = firebaseUserData.getEmail();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
