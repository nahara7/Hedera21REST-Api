package com.nahara.toka.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "tokens")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

    public Token() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "tokenid")
    private String tokenid;

    @Column(name = "name")
    private String name;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "initialsupply")
    private String initialsupply;

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
