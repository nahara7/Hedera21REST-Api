package com.nahara.toka.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import com.hedera.hashgraph.sdk.*;
import java.util.UUID;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

public class PublicUser {
    //to help getting to fields PrivateKey, PublicKey and AccountID
    //maps to same Table as other corresponding class

    public PublicUser() {
    }

    public String id;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("email")
    private String email;
    @SerializedName("accountID")
    private String accountid;

    public String publickey;

    public String privatekey;
    @SerializedName("walletKey")
    private String walletKey;
    @SerializedName("userId")
    private String userId;



    public String getAccountid() {
        return this.accountid;
    }
    public void setWalletKey(String walletKey){
        this.walletKey=walletKey;
    }
    public String getWalletKey(){
        return this.walletKey;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public void setAccountid() throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {

        this.accountid=AccountCreateTransaction(this.publickey).toString();

    }
    public String getPublickey() {
        return this.publickey;
    }
    public void setPublickey(){ this.publickey=PrivateKey.fromString(this.privatekey).getPublicKey().toString();}

    public void setPrivateKey(){ this.privatekey= PrivateKey.generate().toString();}

    public String getPrivateKey(){ return this.privatekey;}
    public String getPublicKey(){return this.publickey;}

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId(){
        return this.id;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getUserId(){
        return this.userId;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId() {

        UUID uniqueKey = UUID.randomUUID();
        this.id = uniqueKey.toString();
    }
    public void setPrivateKey(String privatekey){
        this.privatekey=privatekey;
    }

    public AccountId AccountCreateTransaction(String publicKey) throws HederaReceiptStatusException, TimeoutException, HederaPreCheckStatusException {


        Client client = Client.forTestnet();

        AccountId envId = AccountId.
                fromString(Objects.requireNonNull((System.getenv("NAHARA_ACCOUNT_ID"))));
        PrivateKey envPriv = PrivateKey.
                fromString(Objects.requireNonNull((System.getenv("NAHARA_PRIVATE_KEY"))));
        client.setOperator(envId, envPriv);
        AccountCreateTransaction transaction = new AccountCreateTransaction()
                .setKey(PublicKey.fromString(publickey))
                .setInitialBalance(new Hbar(10));
        TransactionResponse txId = transaction.execute(client);
        TransactionReceipt receipt = txId.getReceipt(client);
        return receipt.accountId;


    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}

