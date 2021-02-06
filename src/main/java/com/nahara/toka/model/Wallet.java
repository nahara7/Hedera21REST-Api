/*package com.newjerseysoftware.hederaDemo.model;
import com.hedera.hashgraph.sdk.Hbar;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "wallet")

public class Wallet {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue
    private Integer id;

    @NotNull(message = "AccountId must be provided")
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "balance",nullable = false)
    @NotNull(message = "Wallet balance must be provided")
    private BigDecimal balance;
    @NotNull(message = "Token Type")
    @ManyToOne
    @JoinColumn(name = "token_id")
    private Token token;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public Wallet(){
    }

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

}*/




