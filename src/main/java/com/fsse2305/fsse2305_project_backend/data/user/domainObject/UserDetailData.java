package com.fsse2305.fsse2305_project_backend.data.user.domainObject;

import com.fsse2305.fsse2305_project_backend.data.user.entity.UserEntity;
import jakarta.persistence.Column;

public class UserDetailData {
    private Integer uid;
    private String firebaseUid;
    private String email;

    public UserDetailData(UserEntity userEntity) {
        this.uid = userEntity.getUid();
        this.firebaseUid = userEntity.getFirebaseUid();
        this.email = userEntity.getEmail();
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
