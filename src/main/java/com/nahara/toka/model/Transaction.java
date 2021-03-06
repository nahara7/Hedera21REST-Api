package com.nahara.toka.model;

import com.hedera.hashgraph.sdk.*;
import com.nahara.toka.service.hedera.api.TransactionAsyncService;
import com.nahara.toka.service.hedera.api.UserService;
import com.nahara.toka.service.hedera.api.VendorService;
import com.sybit.airtable.exception.AirtableException;

public class Transaction {
    //UserService userService= new UserService();
    //VendorService vendorService=new VendorService();
    //TransactionAsyncService transactionAsyncService= new TransactionAsyncService();
    private String userId;
    private String vendorId;
    private long fee;
    private String memo=null;
    //update promotion class


    public Transaction() {
    }
    public Transaction(String userId, String vendorId, long fee, String memo) throws AirtableException {
        UserService userService= new UserService();
        VendorService vendorService=new VendorService();
        TransactionAsyncService transactionAsyncService= new TransactionAsyncService();
        this.userId=userId;
        this.vendorId=vendorId;
        this.fee=fee;
        this.memo=memo;


    }
    public Transaction(String userId, String vendorId, long fee) throws AirtableException {
        UserService userService= new UserService();
        VendorService vendorService=new VendorService();
        TransactionAsyncService transactionAsyncService= new TransactionAsyncService();
        this.userId=userId;
        this.vendorId=vendorId;
        this.fee=fee;


    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public long getFee() {
        return this.fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


    public class Promotion{
        private User user;
        private Vendor vendor;
        private long promotion;
        private String memo;
        public Promotion(User user, Vendor vendor, Long promotion){
            this.user = user;
            this.vendor = vendor;
            this.promotion = promotion;
        }
        public User getUser(){
            return this.user;

        }public Vendor getVendor(){
            return this.getVendor();
        }
        public Long getPromotion(){
            return this.promotion;
        }

        public String getMemo(){
            return this.memo;
        }

    }
}

