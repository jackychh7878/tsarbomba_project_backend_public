package com.fsse2305.fsse2305_project_backend.data.transaction.enumfolder;

public enum Status {
    PREPARE("PREPARE"),
    PROCESSING("PROCESSING"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED");

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
