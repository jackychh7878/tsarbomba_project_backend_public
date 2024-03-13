package com.fsse2305.fsse2305_project_backend.repository;

import com.fsse2305.fsse2305_project_backend.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query(value = "SELECT * FROM user u WHERE u.firebase_uid = ?1", nativeQuery = true)
    Optional<UserEntity> findByFirebaseUid(String firebaseUid);
}
