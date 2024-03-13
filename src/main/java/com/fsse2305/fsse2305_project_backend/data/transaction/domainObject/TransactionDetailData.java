package com.fsse2305.fsse2305_project_backend.data.transaction.domainObject;

import com.fsse2305.fsse2305_project_backend.data.transaction.enumfolder.Status;
import com.fsse2305.fsse2305_project_backend.data.transaction.entity.TransactionEntity;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDetailData {
    private Integer tid;
    private Integer uid;
    private Date datetime;
    private Status status;
    private BigDecimal total;

    public TransactionDetailData(TransactionEntity transactionEntity) {
        this.tid = transactionEntity.getTid();
        this.uid = transactionEntity.getUser().getUid();
        this.datetime = transactionEntity.getDatetime();
        this.status = transactionEntity.getStatus();
        this.total = transactionEntity.getTotal();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

}
