package com.nahara.toka.model;

import com.google.gson.annotations.SerializedName;
import com.hedera.hashgraph.sdk.*;
import com.nahara.toka.service.hedera.api.UserService;
import com.nahara.toka.service.hedera.api.VendorService;
import com.sybit.airtable.exception.AirtableException;

import java.util.UUID;

import java.util.Objects;
import java.util.concurrent.TimeoutException;

public class Account {



    public Account() throws AirtableException {

    }
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
    @SerializedName("userIdAccess")
    private String userIdAccess;
    @SerializedName("vendorIdAccess")
    private String vendorIdAccess;

    public String getUserIdAccess() {
        return userIdAccess;
    }

    public void setUserIdAccess(String userIdAccess) {
        this.userIdAccess = userIdAccess;
    }

    public String getVendorIdAccess() {
        return vendorIdAccess;
    }

    public void setVendorIdAccess(String vendorIdAccess) {
        this.vendorIdAccess = vendorIdAccess;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

