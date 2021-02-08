package com.nahara.toka.model;


import com.google.gson.annotations.SerializedName;

public class Token {

    public Token() {}


    private long id;

    @SerializedName("tokenid")
    private String tokenid;

    @SerializedName("name")
    private String name;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("initalsupply")
    private String initialsupply;

    @SerializedName("supplykey")
    private String supplykey;

    @SerializedName("adminkey")
    private String adminkey;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getInitialsupply() {
        return initialsupply;
    }

    public void setInitialsupply(String initialsupply) {
        this.initialsupply = initialsupply;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
