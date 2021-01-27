package com.newjerseysoftware.hederaDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hedera.hashgraph.sdk.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

@Entity
@Table(name = "vendors")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vendor {

    public Vendor() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "accountid")
    private String accountid;

    @Column(name = "publickey")
    private String publickey;

    @Column(name = "privatekey")
    private String privatekey;


    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }
    public void setAccountid() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {
        this.accountid=AccountCreateTransaction(this.publickey).toString();}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPublickey() {
        return publickey;
    }

    public void setPublicKey(){
        this.publickey= com.hedera.hashgraph.sdk.PrivateKey.fromString(this.privatekey).getPublicKey().toString();
    }
    public void setPrivateKey(){
        this.privatekey= PrivateKey.generate().toString();
    }
    public String getPrivateKey(){ return this.privatekey;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public AccountId AccountCreateTransaction(String publickey) throws TimeoutException, HederaPreCheckStatusException, HederaReceiptStatusException {
        Client client=Client.forTestnet();
        //add environment variables
        AccountId envId=AccountId.fromString(Objects.requireNonNull((System.getenv("nahara_account_id"))));
        PrivateKey envPriv=PrivateKey.fromString(Objects.requireNonNull((System.getenv("nahara_private_key"))));
        client.setOperator(envId, envPriv);
        AccountCreateTransaction transaction = new AccountCreateTransaction()
                .setKey(PublicKey.fromString(publickey))
                .setInitialBalance( new Hbar(70));
        TransactionResponse txId= transaction.execute(client);
        TransactionReceipt receipt= txId.getReceipt(client);
        return receipt.accountId;
    }
}
