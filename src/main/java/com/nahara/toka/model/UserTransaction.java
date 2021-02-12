package com.nahara.toka.model;



import com.hedera.hashgraph.sdk.*;
import com.nahara.toka.service.hedera.api.TransactionAsyncService;
import com.nahara.toka.service.hedera.api.UserService;
import com.nahara.toka.service.hedera.api.VendorService;
import com.sybit.airtable.exception.AirtableException;

public class UserTransaction {

    private String senderId;
    private String recipientId;
    private long fee;
    private String memo=null;


    public UserTransaction() {
    }
    public UserTransaction(String senderId, String recipientId, long fee) throws AirtableException {
        UserService userService = new UserService();
        TransactionAsyncService transactionAsyncService = new TransactionAsyncService();
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.fee = fee;
    }
    public UserTransaction(String senderId, String recipientId, long fee, String memo) throws AirtableException {
        UserService userService = new UserService();
        TransactionAsyncService transactionAsyncService = new TransactionAsyncService();
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.fee = fee;
        this.memo=memo;


    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}

