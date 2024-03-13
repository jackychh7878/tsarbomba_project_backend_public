package com.fsse2305.fsse2305_project_backend.data.transaction.dto.response;

import com.fsse2305.fsse2305_project_backend.data.transaction.domainObject.TransactionDetailData;
import com.fsse2305.fsse2305_project_backend.data.transaction.enumfolder.Status;

public class TransactionProcessingResponseDto {
    private String result;

    public TransactionProcessingResponseDto(TransactionDetailData data){
        setResult(data);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setResult(TransactionDetailData data){
        if (data.getStatus().equals(Status.PROCESSING)){
            this.result = "SUCCESS";
        }
    }
}
