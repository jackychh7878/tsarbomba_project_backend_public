package com.fsse2305.fsse2305_project_backend.data.transaction.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fsse2305.fsse2305_project_backend.data.transaction.domainObject.TransactionProductEntityData;
import com.fsse2305.fsse2305_project_backend.data.transaction.enumfolder.Status;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@JsonPropertyOrder({"tid", "uid", "datetime", "status", "total"})
public class TransactionResponseDto {
    private Integer tid;
    @JsonProperty("buyer_uid")
    private Integer uid;
    @JsonFormat(pattern = "yyyyMMdd'T'HH:mm:ss")
    private Date datetime;
    private Status status;
    private BigDecimal total;
    private String priceId;
    @JsonProperty("items")
    private List<TransactionProductResponseDto> transactionProductResponseDtoList = new ArrayList<>();

    public TransactionResponseDto(List<TransactionProductEntityData> data) {
        this.tid = data.get(0).getTransaction().getTid();
        this.uid = data.get(0).getTransaction().getUid();
//        setDateTime(data.get(0));
        this.datetime = data.get(0).getTransaction().getDatetime();
        this.status = data.get(0).getTransaction().getStatus();
        this.total = data.get(0).getTransaction().getTotal();
        setTransactionProductResponseDtoList(data);
    }

    public List<TransactionProductResponseDto> getTransactionProductResponseDtoList() {
        return transactionProductResponseDtoList;
    }

    public void setTransactionProductResponseDtoList(List<TransactionProductEntityData> data){
        for (TransactionProductEntityData transactionProductEntityData: data){
            TransactionProductResponseDto dto = new TransactionProductResponseDto(transactionProductEntityData);
            transactionProductResponseDtoList.add(dto);
        }
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
