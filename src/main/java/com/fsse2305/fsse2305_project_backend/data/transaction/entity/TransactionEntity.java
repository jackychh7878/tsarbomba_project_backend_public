package com.fsse2305.fsse2305_project_backend.data.transaction.entity;

import com.fsse2305.fsse2305_project_backend.data.transaction.enumfolder.Status;
import com.fsse2305.fsse2305_project_backend.data.user.entity.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity user;
    @Column(nullable = false)
    private Date datetime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private BigDecimal total;
    @OneToMany(mappedBy = "transaction")
    @Column(nullable = false)
    private List<TransactionProductEntity> transactionProductEntityList = new ArrayList<>();

    public TransactionEntity(){}

    //Fix the constructor
    public TransactionEntity(UserEntity userEntity, Status status, BigDecimal total){
        this.user = userEntity;
        this.datetime = new Date(System.currentTimeMillis());
        this.status = status;
        this.total = total;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductEntity> getTransactionProductEntityList() {
        return transactionProductEntityList;
    }

    public void setTransactionProductEntityList(List<TransactionProductEntity> transactionProductEntityList) {
        this.transactionProductEntityList = transactionProductEntityList;
    }
}
