package com.nahara.toka.model;

import com.nahara.toka.model.Token;
import com.nahara.toka.model.Transaction;
import com.nahara.toka.model.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Wallet {

    public Wallet(){}



    private Integer id;



    private String accountId;

    private BigDecimal balance;


    private Token token;


    private Date lastUpdated;


    private String lastUpdatedBy;


    private List<Transaction> transactions;

    private String walletKey;


    public Wallet(String accountId, Token token, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.token = token;
        this.lastUpdated = new Date();
    }

    public Wallet(String accountId, Token token, BigDecimal balance, String lastUpdatedBy) {
        this(accountId, token,balance);
        this.lastUpdatedBy = lastUpdatedBy;
    }
    public Wallet(User user, Token token){
        this.accountId=user.getAccountid();
        this.token=token;
        this.balance= BigDecimal.valueOf(0.0);


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Token getToken() {
        return this.token;
    }

    public void setToken (Token token) {
        this.token = token;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String userId) {
        this.accountId = accountId;
    }

    public String getWalletKey() {
        return walletKey;
    }

    public void setWalletKey(String walletKey) {
        this.walletKey = walletKey;
    }
}




